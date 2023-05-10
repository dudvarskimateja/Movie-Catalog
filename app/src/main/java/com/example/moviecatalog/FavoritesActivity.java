package com.example.moviecatalog;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Get the favorites list from the intent
        ArrayList<Movie> favoritesList = (ArrayList<Movie>) getIntent().getSerializableExtra("favoritesList");

        // Create an adapter for the list view
        FavoritesListAdapter adapter = new FavoritesListAdapter(this, R.layout.favorites_list_view, favoritesList);

        // Set the adapter to the list view
        ListView listView = findViewById(R.id.favorites_list_view);
        listView.setAdapter(adapter);

        // Set a click listener for the list view items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: Handle list view item clicks
            }
        });
    }
}
