package com.codingwithjks.paggingmovies.Modal;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies_detail",indices = @Index(value = {"movie_detail_id","database_movies_detail_id"}, unique = true))
public class MoviesDetail {
    @PrimaryKey(autoGenerate = true)
    private int database_movies_detail_id;

    @SerializedName("id")
    private int movie_detail_id;

    @SerializedName("name")
    private String movies_name;

    @SerializedName("image")
    private String poster_name;

    @SerializedName("rating")
    private float movie_rating;

    @SerializedName("description")
    private String movie_description;

    public MoviesDetail(int movie_detail_id, String movies_name, String poster_name, float movie_rating, String movie_description) {
        this.movie_detail_id = movie_detail_id;
        this.movies_name = movies_name;
        this.poster_name = poster_name;
        this.movie_rating = movie_rating;
        this.movie_description = movie_description;
    }

    public int getDatabase_movies_detail_id() {
        return database_movies_detail_id;
    }

    public void setDatabase_movies_detail_id(int database_movies_detail_id) {
        this.database_movies_detail_id = database_movies_detail_id;
    }

    public int getMovie_detail_id() {
        return movie_detail_id;
    }

    public void setMovie_detail_id(int movie_detail_id) {
        this.movie_detail_id = movie_detail_id;
    }

    public String getMovies_name() {
        return movies_name;
    }

    public void setMovies_name(String movies_name) {
        this.movies_name = movies_name;
    }

    public String getPoster_name() {
        return poster_name;
    }

    public void setPoster_name(String poster_name) {
        this.poster_name = poster_name;
    }

    public float getMovie_rating() {
        return movie_rating;
    }

    public void setMovie_rating(float movie_rating) {
        this.movie_rating = movie_rating;
    }

    public String getMovie_description() {
        return movie_description;
    }

    public void setMovie_description(String movie_description) {
        this.movie_description = movie_description;
    }

    @Override
    public String toString() {
        return "MoviesDetail{" +
                "id=" + database_movies_detail_id+
                ", movie_detail_id=" + movie_detail_id +
                ", movies_name='" + movies_name + '\'' +
                ", poster_name='" + poster_name + '\'' +
                ", movie_rating=" + movie_rating +
                ", movie_description='" + movie_description + '\'' +
                '}';
    }
}
