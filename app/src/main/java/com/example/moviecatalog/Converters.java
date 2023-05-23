package com.example.moviecatalog;

import android.os.Build;

import androidx.room.TypeConverter;
import java.time.LocalDate;

public class Converters {
    @TypeConverter
    public static LocalDate fromTimestamp(String value) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return value == null ? null : LocalDate.parse(value);
        }
        return null;
    }

    @TypeConverter
    public static String dateToTimestamp(LocalDate date) {
        // Convert LocalDate object to a database-compatible string representation
        return date == null ? null : date.toString();
    }
}

