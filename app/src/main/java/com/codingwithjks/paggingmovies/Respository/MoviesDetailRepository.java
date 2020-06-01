package com.codingwithjks.paggingmovies.Respository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.codingwithjks.paggingmovies.Database.MoviesDatabase;
import com.codingwithjks.paggingmovies.Modal.MoviesDetail;

import java.util.List;

public class MoviesDetailRepository {

    private MoviesDatabase moviesDatabase;
    private LiveData<List<MoviesDetail>> getAllMoviesDetail;

    public MoviesDetailRepository(Application application)
    {
      moviesDatabase=MoviesDatabase.getInstance(application);
      getAllMoviesDetail=moviesDatabase.moviesDao().getAllMoviesDetails();
    }

    public LiveData<List<MoviesDetail>> getAllMovieDetails()
    {
        return getAllMoviesDetail;
    }
}
