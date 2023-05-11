package com.example.moviecatalog;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @TypeConverter
    public static LocalDate fromString(String value) {
        return value == null ? null : LocalDate.parse(value, formatter);
    }

    @TypeConverter
    public static String toString(LocalDate date) {
            return date == null ? null : date.format(formatter);
    }
}
