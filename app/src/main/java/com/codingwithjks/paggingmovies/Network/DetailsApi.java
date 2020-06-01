package com.codingwithjks.paggingmovies.Network;

import com.codingwithjks.paggingmovies.Modal.MoviesDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DetailsApi {

    @GET("movies-detail.php")
    Call<List<MoviesDetail>> getAllMoviesDetails();
}
