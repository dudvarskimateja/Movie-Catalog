package com.example.moviecatalog;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MovieDetails extends AppCompatActivity {

    private TextView movieTitleTextView;
    private TextView budgetTextView;
    private TextView genresTextView;
    private TextView homepageTextView;
    private TextView spokenLanguagesTextView;
    private TextView productionCompaniesTextView;
    private TextView productionCountriesTextView;
    private TextView revenueTextView;
    private TextView runtimeTextView;
    private TextView originalLanguageTextView;
    private TextView statusTextView;
    private TextView taglineTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Movie Details");
        }



        // Find all the TextViews in the UI
        movieTitleTextView = findViewById(R.id.movieTitleTextView);
        budgetTextView = findViewById(R.id.budgetTextView);
        genresTextView = findViewById(R.id.genresTextView);
        homepageTextView = findViewById(R.id.homepageTextView);
        spokenLanguagesTextView = findViewById(R.id.spokenLanguagesTextView);
        productionCompaniesTextView = findViewById(R.id.productionCompaniesTextView);
        productionCountriesTextView = findViewById(R.id.productionCountriesTextView);
        revenueTextView = findViewById(R.id.revenueTextView);
        runtimeTextView = findViewById(R.id.runtimeTextView);
        originalLanguageTextView = findViewById(R.id.originalLanguageTextView);
        statusTextView = findViewById(R.id.statusTextView);
        taglineTextView = findViewById(R.id.taglineTextView);



        // Get the movie object from the intent extra
        Movie currentMovie = (Movie) getIntent().getSerializableExtra("movie");

        // Make a Volley request to get the movie details by ID
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://app-vpigadas.herokuapp.com/api/movies/demo/" + currentMovie.getId() + "/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Set the movie details in the UI
                            movieTitleTextView.setText(response.getString("title"));
                            budgetTextView.setText("Budget: $" + String.valueOf(response.getInt("budget")));
                            //genres
                            JSONArray genresArray = response.getJSONArray("genres");
                            String genres = "";

                            for (int i = 0; i < genresArray.length(); i++) {
                                JSONObject genreObject = genresArray.getJSONObject(i);
                                String genreName = genreObject.getString("name");
                                genres += genreName + ", ";
                            }

                            // Remove the trailing comma and space
                            if (!genres.isEmpty()) {
                                genres = genres.substring(0, genres.length() - 2);
                            }

                            genresTextView.setText("Genres: " + genres);
                            homepageTextView.setText(response.getString("homepage"));
                            originalLanguageTextView.setText(response.getString("original_language"));
                            productionCompaniesTextView.setText(response.getJSONArray("production_companies").toString());

                            //production countries
                            JSONArray countriesArray = response.getJSONArray("production_countries");
                            StringBuilder countriesString = new StringBuilder();
                            for (int i = 0; i < countriesArray.length(); i++) {
                                JSONObject countryObj = countriesArray.getJSONObject(i);
                                String countryName = countryObj.getString("name");
                                countriesString.append(countryName);
                                if (i < countriesArray.length() - 1) {
                                    countriesString.append("\n");
                                }
                            }
                            productionCountriesTextView.setText("Production Countries: \n" + countriesString.toString());

                            revenueTextView.setText("Revenue: $" + String.valueOf(response.getInt("revenue")));
                            runtimeTextView.setText("Runtime: " + String.valueOf(response.getInt("runtime")) + "min");
                            spokenLanguagesTextView.setText(response.getJSONArray("spoken_languages").toString());
                            statusTextView.setText("Status: " + response.getString("status"));
                            taglineTextView.setText(response.getString("tagline"));
                        } catch (JSONException e) {
                            Log.e("MovieDetailActivity", "Error parsing JSON response", e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MovieDetailActivity", "Error fetching movie data", error);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

