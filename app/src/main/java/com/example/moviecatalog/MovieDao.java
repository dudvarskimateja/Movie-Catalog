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

    Movie findByName(String title);

    @Insert
    void insertAll(Movie... movies);

    @Delete
    void delete(Movie movie);
}
