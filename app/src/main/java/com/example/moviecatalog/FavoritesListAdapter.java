package com.example.moviecatalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;

import java.util.List;

public class FavoritesListAdapter extends ArrayAdapter<Movie> {

    private
    Context context;
    private int resource;
    private List<Movie> movies;

    public FavoritesListAdapter(Context context, int resource, List<Movie> movies) {
        super(context, resource, movies);
        this.context = context;
        this.resource = resource;
        this.movies = movies;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }



//        ImageView moviePoster = convertView.findViewById(R.id.movie_poster);
//        TextView movieTitle = convertView.findViewById(R.id.movie_title);
//        TextView movieReleaseDate = convertView.findViewById(R.id.movie_release_date);
//
//        Movie movie = movies.get(position);
//        Glide.with(context).load(movie.getPoster()).into(moviePoster);
//        movieTitle.setText(movie.getTitle());
//        movieReleaseDate.setText(movie.getReleaseDate().toString());

        return convertView;
    }
}

