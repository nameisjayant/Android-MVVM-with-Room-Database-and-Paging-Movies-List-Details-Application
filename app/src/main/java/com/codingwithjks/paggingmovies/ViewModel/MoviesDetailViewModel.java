package com.codingwithjks.paggingmovies.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.codingwithjks.paggingmovies.Modal.MoviesDetail;
import com.codingwithjks.paggingmovies.Respository.MoviesDetailRepository;
import com.codingwithjks.paggingmovies.Respository.MoviesRepository;

import java.util.List;

public class MoviesDetailViewModel extends AndroidViewModel {

    private LiveData<List<MoviesDetail>> moviesDetailLiveData;
    private MoviesDetailRepository moviesRepository;

    public MoviesDetailViewModel(@NonNull Application application) {
        super(application);
        moviesRepository=new MoviesDetailRepository(application);
        moviesDetailLiveData=moviesRepository.getAllMovieDetails();
    }

    public LiveData<List<MoviesDetail>> getAllMoviesDetail()
    {
        return moviesDetailLiveData;
    }
}
