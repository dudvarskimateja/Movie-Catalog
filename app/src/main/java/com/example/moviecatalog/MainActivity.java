package com.example.moviecatalog;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jakewharton.threetenabp.AndroidThreeTen;

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
        AndroidThreeTen.init(this);


        final MovieDatabase db = Room.databaseBuilder(getApplicationContext(),
                MovieDatabase.class, "movie").build();

        MovieDao movieDao = db.movieDao();
        List<Movie> movie = movieDao.getAll();


        movieRecyclerView = findViewById(R.id.recycler_view);
        movieAdapter = new MovieAdapter(this, movieList);

        movieRecyclerView.setAdapter(movieAdapter);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://app-vpigadas.herokuapp.com/api/movies/demo/";

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

                                releaseDate = LocalDate.parse(movieObj.getString("release_date"));

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
                }, error -> Log.e("MainActivity", "Error fetching movie data", error)) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        queue.add(jsonObjectRequest);

    }
}
