package com.example.moviecatalog;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName ="movies")
public class Movie implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "overview")
    private String overview;
    @ColumnInfo(name = "popularity")
    private Double popularity;
    @ColumnInfo(name = "voteCount")
    private int voteCount;
    @ColumnInfo(name = "voteAverage")
    private Double voteAverage;
    @ColumnInfo(name = "releaseDate")
    private LocalDate releaseDate;
    @ColumnInfo(name = "poster")
    private String poster;

    public Movie(int id, String title, String overview, Double popularity, int voteCount, Double voteAverage, LocalDate releaseDate, String poster) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVote_count(int voteCount) {
        this.voteCount = voteCount;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVote_average(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    private static List<Movie> favoriteMovies = new ArrayList<>();

    public static List<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public static void addToFavorites(Movie movie) {
        favoriteMovies.add(movie);
    }

    public static void removeFromFavorites(Movie movie) {
        favoriteMovies.remove(movie);
    }

    public static boolean isFavorite(Movie movie) {
        return favoriteMovies.contains(movie);
    }
}
