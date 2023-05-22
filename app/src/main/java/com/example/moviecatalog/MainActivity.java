package com.example.moviecatalog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView movieRecyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Movie Maven");

        // bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    Intent homeIntent = new Intent(this, MainActivity.class);
                    startActivity(homeIntent);
                    return true;
                case R.id.action_favorite:
                    Intent favoritesIntent = new Intent(this, FavoritesActivity.class);
                    startActivity(favoritesIntent);
                    return true;
                default:
                    return false;
            }
        });

//        Button addFavoriteButton = findViewById(R.id.add_favorite_button);
//        addFavoriteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get the selected movie
//                Movie selectedMovie = null;// get the selected movie from the main activity
//                        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
//                // Add the selected movie to the intent
//                intent.putExtra("selectedMovie", selectedMovie);
//                // Start the FavoritesActivity
//                startActivity(intent);
//            }
//        });



        movieRecyclerView = findViewById(R.id.recycler_view);
        movieAdapter = new MovieAdapter(this, movieList);

        movieRecyclerView.setAdapter(movieAdapter);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://app-vpigadas.herokuapp.com/api/movies/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject movieObj = jsonArray.getJSONObject(i);
                                int id = movieObj.getInt("id");
                                String title = movieObj.getString("title");
                                String overview = movieObj.getString("overview");
                                LocalDate releaseDate = null;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    releaseDate = LocalDate.parse(movieObj.getString("release_date"));
                                }
                                String poster = movieObj.getString("poster_path");
                                double popularity = movieObj.getDouble("popularity");
                                int voteCount = movieObj.getInt("vote_count");
                                double voteAverage = movieObj.getDouble("vote_average");

                                Movie movie = new Movie(id, title, overview, popularity, voteCount, voteAverage, releaseDate, poster);

                                movieList.add(movie);
                                movieAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            Log.e("MainActivity", "Error parsing JSON response", e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MainActivity", "Error fetching movie data", error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu, menu);
        ArrayList<Movie> favoritesList = new ArrayList<>();
        MenuItem favoritesMenuItem = menu.findItem(R.id.action_favorite);
        favoritesMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent favoritesIntent = new Intent(MainActivity.this, FavoritesActivity.class);
                favoritesIntent.putExtra("favoritesList", favoritesList);
                startActivity(favoritesIntent);
                return true;
            }
        });

        return true;
    }



}