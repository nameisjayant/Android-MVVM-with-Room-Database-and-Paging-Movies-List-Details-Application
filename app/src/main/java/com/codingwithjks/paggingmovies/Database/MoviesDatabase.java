package com.codingwithjks.paggingmovies.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.codingwithjks.paggingmovies.Dao.MoviesDao;
import com.codingwithjks.paggingmovies.Modal.Movies;
import com.codingwithjks.paggingmovies.Modal.MoviesDetail;

import java.security.PublicKey;
import java.util.List;

@Database(entities = {Movies.class, MoviesDetail.class},version = 10)

public abstract class MoviesDatabase extends RoomDatabase {

    private static final String DATABASE_NAME="Movies_list";
    public abstract MoviesDao moviesDao();


    private static volatile MoviesDatabase INSTANCE=null;

    public static MoviesDatabase getInstance(Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (MoviesDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE= Room.databaseBuilder(context,MoviesDatabase.class,
                            DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };

    static class PopulateAsynTask extends AsyncTask<Void,Void,Void>
    {
        private MoviesDao moviesDao;

        private PopulateAsynTask(MoviesDatabase moviesDatabase){
            moviesDao=moviesDatabase.moviesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            moviesDao.deleteAll();
            return null;
        }
    }
}
