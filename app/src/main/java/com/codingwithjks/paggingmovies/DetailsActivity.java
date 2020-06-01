package com.codingwithjks.paggingmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codingwithjks.paggingmovies.Listener.Listener;
import com.codingwithjks.paggingmovies.Modal.MoviesDetail;
import com.codingwithjks.paggingmovies.Network.DetailsApi;
import com.codingwithjks.paggingmovies.Respository.MoviesRepository;
import com.codingwithjks.paggingmovies.ViewModel.MoviesDetailViewModel;
import com.codingwithjks.paggingmovies.ViewModel.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {
 public static final String DATA_URL="http://www.codingwithjks.tech/movies-detail.php/";
    private static final String TAG ="main" ;
    private MoviesRepository repository;
 private int position,get_id;
 private List<MoviesDetail> moviesDetails;
 private TextView name_details,description_details,rating_details;
 private ImageView poster_details;
 private MoviesDetailViewModel moviesDetailViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name_details=findViewById(R.id.name_detail);
        rating_details=findViewById(R.id.rating_detail);
        description_details=findViewById(R.id.description_detail);
        poster_details=findViewById(R.id.image_detail);
        repository=new MoviesRepository(getApplication());
        moviesDetails=new ArrayList<>();
        moviesDetailViewModel=new ViewModelProvider(this).get(MoviesDetailViewModel.class);
        getMoviesDetails();
        setData();
    }

    private void setData() {
        Bundle bundle=getIntent().getExtras();

        if(bundle !=null)
        {
            position=bundle.getInt("position",0);
            get_id=bundle.getInt("id",0);
            moviesDetailViewModel.getAllMoviesDetail().observe(this, new Observer<List<MoviesDetail>>() {
                @Override
                public void onChanged(List<MoviesDetail> moviesDetailList) {
                 Glide.with(getApplicationContext())
                           .load(moviesDetailList.get(position).getPoster_name())
                           .into(poster_details);
                   name_details.setText(moviesDetailList.get(position).getMovies_name());
                   rating_details.setText(""+moviesDetailList.get(position).getMovie_rating());
                   description_details.setText(moviesDetailList.get(position).getMovie_description());

                    Log.d(TAG, "onChanged: "+moviesDetailList.get(position).getMovies_name());
                }
            });

        }
    }

    private void getMoviesDetails() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(DATA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DetailsApi api=retrofit.create(DetailsApi.class);
        Call<List<MoviesDetail>> call=api.getAllMoviesDetails();
        call.enqueue(new Callback<List<MoviesDetail>>() {
            @Override
            public void onResponse(Call<List<MoviesDetail>> call, Response<List<MoviesDetail>> response) {
                if(response.isSuccessful())
                {
                   repository.insertMoviesDetail(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MoviesDetail>> call, Throwable t) {
                Log.d("main", "onFailure: "+t.getMessage());
            }
        });
    }
}