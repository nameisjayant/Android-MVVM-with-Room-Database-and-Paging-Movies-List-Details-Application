package com.codingwithjks.paggingmovies.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.codingwithjks.paggingmovies.Dao.MoviesDao;
import com.codingwithjks.paggingmovies.Database.MoviesDatabase;
import com.codingwithjks.paggingmovies.Modal.Movies;
import com.codingwithjks.paggingmovies.Modal.MoviesDetail;
import com.codingwithjks.paggingmovies.Respository.MoviesRepository;

public class MoviesViewModel extends AndroidViewModel {

    private MoviesDao moviesDao;

    public final LiveData<PagedList<Movies>> pagedListLiveData;

    public MoviesViewModel(@NonNull Application application) {
        super(application);

        moviesDao= MoviesDatabase.getInstance(application).moviesDao();
        pagedListLiveData=new LivePagedListBuilder<>(
                moviesDao.getAllMovies(),10
        ).build();


    }


}
