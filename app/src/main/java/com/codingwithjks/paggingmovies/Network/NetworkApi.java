package com.codingwithjks.paggingmovies.Network;

import androidx.paging.PagedList;

import com.codingwithjks.paggingmovies.Modal.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("/movies.php")
    Call<List<Movies>> getAllMovies();
}
