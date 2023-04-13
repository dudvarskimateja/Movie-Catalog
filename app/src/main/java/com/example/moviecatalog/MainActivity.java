package com.example.moviecatalog;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //volley api settings
        queue = Volley.newRequestQueue(this);
        String url = "https://app-vpigadas.herokuapp.com/api/movies/demo/";

        //navigation settings
        navController = Navigation.findNavController(this, R.id.nav_graph);
        NavigationUI.setupActionBarWithNavController(this, navController);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("movies");
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

                                // TODO: Add the movie to your list or display it in your UI
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
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, new AppBarConfiguration.Builder(R.id.movieFragment).build())
                || super.onSupportNavigateUp();
    }
}
