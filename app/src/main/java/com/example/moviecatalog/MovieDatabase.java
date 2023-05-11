package com.example.moviecatalog;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities={Movie.class}, version=1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
