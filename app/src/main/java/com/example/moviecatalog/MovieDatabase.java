package com.example.moviecatalog;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.List;

@Database(entities={Movie.class}, version=1)
@TypeConverters(LocalDateConverter.class)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
