package com.codingwithjks.paggingmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codingwithjks.paggingmovies.Adapter.MoviesAdapter;
import com.codingwithjks.paggingmovies.Listener.Listener;
import com.codingwithjks.paggingmovies.Modal.Movies;
import com.codingwithjks.paggingmovies.Network.NetworkApi;
import com.codingwithjks.paggingmovies.Respository.MoviesRepository;
import com.codingwithjks.paggingmovies.ViewModel.MoviesViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Listener {

    private static final String URL_DATA="http://www.codingwithjks.tech/movies.php/";
    private MoviesViewModel moviesViewModel;
    private MoviesAdapter moviesAdapter;
    private RecyclerView recyclerView;
    private MoviesRepository moviesRepository;
    private PagedList<Movies> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        moviesRepository=new MoviesRepository(getApplication());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        moviesAdapter=new MoviesAdapter(this,this::onClickItemListener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        moviesViewModel=new ViewModelProvider(this).get(MoviesViewModel.class);
        moviesViewModel.pagedListLiveData.observe(this, new Observer<PagedList<Movies>>() {
            @Override
            public void onChanged(PagedList<Movies> moviesPagedList) {
                moviesAdapter.submitList(moviesPagedList);
                recyclerView.setAdapter(moviesAdapter);
               movies=moviesPagedList;
            }
        });
        saveDataIntoDatabase();
    }

    private void saveDataIntoDatabase() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL_DATA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkApi api=retrofit.create(NetworkApi.class);
       Call<List<Movies>> call=api.getAllMovies();
       call.enqueue(new Callback<List<Movies>>() {
           @Override
           public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
              if(response.isSuccessful())
              {
                  Log.d("main", "onResponse: "+response.body());
                  moviesRepository.Insert(response.body());
              }
           }

           @Override
           public void onFailure(Call<List<Movies>> call, Throwable t) {
               Log.d("main", "onFailure: "+t.getMessage());
           }
       });

    }

    @Override
    public void onClickItemListener(int position) {
        Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("id",movies.get(position).getMovie_id());
        startActivity(intent);
    }
}
