package com.example.moviecatalog;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Query("SELECT * FROM movie WHERE id = :id")
    Movie getById(int id);

    @Insert
    void insertAll(Movie... movies);

    @Delete
    void delete(Movie movie);

    @Insert
    void addToFavorites(Movie movie);

    @Insert
    void insert(Movie movie);
}

