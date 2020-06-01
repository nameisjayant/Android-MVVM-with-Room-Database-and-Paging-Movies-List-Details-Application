package com.codingwithjks.paggingmovies.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codingwithjks.paggingmovies.Listener.Listener;
import com.codingwithjks.paggingmovies.Modal.Movies;
import com.codingwithjks.paggingmovies.R;

public class MoviesAdapter extends PagedListAdapter<Movies, MoviesAdapter.MoviesViewHolder> {

    private Context context;
    private static Listener listener;
    public MoviesAdapter(Context context,Listener listener)
    {
        super(itemCallback);
        this.listener=listener;
        this.context=context;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.each_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Movies movies=getItem(position);
        holder.name.setText(movies.getMovie_name());
        holder.rating.setText("rating : "+movies.getRating());
        Glide.with(context)
                .load(movies.getPoster())
                .into(holder.poster);
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder{
        TextView name,rating;
        ImageView poster;
        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            rating=itemView.findViewById(R.id.rating);
            poster=itemView.findViewById(R.id.poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  listener.onClickItemListener(getAdapterPosition());
                }
            });
        }
    }

    private static DiffUtil.ItemCallback<Movies> itemCallback=new DiffUtil.ItemCallback<Movies>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
            return oldItem.getDatabase_id() == newItem.getDatabase_id();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
            return oldItem.equals(newItem);
        }
    };
}
