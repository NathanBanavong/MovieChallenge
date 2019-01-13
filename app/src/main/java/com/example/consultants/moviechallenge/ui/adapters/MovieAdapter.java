package com.example.consultants.moviechallenge.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.consultants.moviechallenge.R;
import com.example.consultants.moviechallenge.data.repository.MovieDB;
import com.example.consultants.moviechallenge.ui.viewholder.MovieViewHolder;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = MovieAdapter.class.getSimpleName() + "_TAG";
    List<MovieDB> movieList;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_list_item, viewGroup, false);
        viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MovieDB movieDB = movieList.get(i);
//        TODO need to also have a detail view when clicked
        //case appropriate viewHolder
        MovieViewHolder movieViewHolder = (MovieViewHolder) viewHolder;
        movieViewHolder.tvTitle.setText(movieDB.getTitle());
        Glide.with(viewHolder.itemView.getContext()).load(movieDB.getPosterPath()).into(movieViewHolder.ivMovie);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void addAll(List<MovieDB> books) {
        this.movieList.clear();
        this.movieList.addAll(books);
        notifyDataSetChanged();
    }


}
