package com.example.moviecatalog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> mMovies;

    public MovieAdapter(MainActivity mainActivity, List<Movie> movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_widget, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie currentMovie = mMovies.get(position);
        holder.titleTextView.setText(currentMovie.getTitle());
        holder.overviewTextView.setText(currentMovie.getOverview());
        holder.releaseDateTextView.setText(currentMovie.getReleaseDate().toString());
        Picasso.get().load(currentMovie.getPoster()).into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView posterImageView;
        public TextView titleTextView;
        public TextView overviewTextView;
        public TextView releaseDateTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.moviePosterImageView);
            titleTextView = itemView.findViewById(R.id.movieTitleTextView);
            overviewTextView = itemView.findViewById(R.id.movieOverviewTextView);
            releaseDateTextView = itemView.findViewById(R.id.movieReleaseDateTextView);
        }
    }
}


