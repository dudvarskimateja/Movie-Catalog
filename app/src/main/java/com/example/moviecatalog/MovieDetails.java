package com.example.moviecatalog;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

        // Get the movie ID from the intent extra
        int movieId = getIntent().getIntExtra("MOVIE_ID", -1);

        if (movieId != -1) {
            // Make a Volley request to get the movie details by ID
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://app-vpigadas.herokuapp.com/api/movies/demo/" + movieId + "/";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                // Set the movie details in the UI
                                movieTitleTextView.setText(response.getString("title"));
                                budgetTextView.setText(String.valueOf(response.getInt("budget")));
                                genresTextView.setText(response.getJSONArray("genres").toString());
                                homepageTextView.setText(response.getString("homepage"));
                                originalLanguageTextView.setText(response.getString("original_language"));
                                productionCompaniesTextView.setText(response.getJSONArray("production_companies").toString());
                                productionCountriesTextView.setText(response.getJSONArray("production_countries").toString());
                                revenueTextView.setText(String.valueOf(response.getInt("revenue")));
                                runtimeTextView.setText(String.valueOf(response.getInt("runtime")));
                                spokenLanguagesTextView.setText(response.getJSONArray("spoken_languages").toString());
                                statusTextView.setText(response.getString("status"));
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
    }
}
