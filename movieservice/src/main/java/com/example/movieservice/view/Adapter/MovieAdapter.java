package com.example.movieservice.view.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieservice.R;
import com.example.movieservice.data.Movies;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public static final String TAG = MovieAdapter.class.getSimpleName() + "_TAG";
    List<Movies> movieList;

    public MovieAdapter(List<Movies> movies) {
        this.movieList = movies;
    }

    public void setMovies(List<Movies> movies) {
        this.movieList = movies;
        notifyDataSetChanged();
    }

    //    TODO need to reference the items clicked by the user -> see 'amazon' example (example06_2)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        RecyclerView.ViewHolder viewHolder = null;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_list_item, viewGroup, false);
//        viewHolder = new MovieViewHolder(view);

        return new ViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        MovieViewHolder movieViewHolder = (MovieViewHolder) viewHolder;
//        movieViewHolder.tvTitle.setText(movieDB.getTitle());
//        Glide.with(viewHolder.itemView.getContext()).load(movieDB.getPosterPath()).into(movieViewHolder.ivMovie);
//
//    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Movies movies = movieList.get(i);
//        TODO need to also have a detail view when clicked
        //case appropriate viewHolder
        viewHolder.tvTitle.setText(movies.getTitle());
        Glide.with(viewHolder.itemView.getContext()).
                load(movies.getPosterPath()).
                into(viewHolder.ivMoviePoster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void addAll(List<Movies> books) {
        this.movieList.clear();
        this.movieList.addAll(books);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView ivMoviePoster;
        final TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            ivMoviePoster = itemView.findViewById(R.id.ivMovie);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
