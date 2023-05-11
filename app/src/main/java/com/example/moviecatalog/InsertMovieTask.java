package com.example.moviecatalog;

import android.os.AsyncTask;

public class InsertMovieTask extends AsyncTask<Movie, Void, Void> {
    private MovieDao movieDao;

    public InsertMovieTask(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        movieDao.insertAll(movies);
        return null;
    }
}
