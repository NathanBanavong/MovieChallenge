package com.example.consultants.moviechallenge.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.consultants.moviechallenge.R;

public class MovieDetailHolder extends RecyclerView.ViewHolder {
    public ImageView ivMoviePoster;
    public TextView tvTitle;
    public TextView tvOriginTitle;
    public TextView tvGenre;
    public TextView tvSummary;
    public TextView tvPopularity;
    public TextView tvReleaseDate;
    public TextView tvRunTime;
    public TextView tvVoteAvg;


    public MovieDetailHolder(@NonNull View itemView) {
        super(itemView);
        this.ivMoviePoster = itemView.findViewById(R.id.ivMoviePoster);
        this.tvTitle = itemView.findViewById(R.id.tvTitle);
        this.tvOriginTitle = itemView.findViewById(R.id.tvOriginTitle);
        this.tvGenre = itemView.findViewById(R.id.tvGenre);
        this.tvSummary = itemView.findViewById(R.id.tvSummary);
        this.tvPopularity = itemView.findViewById(R.id.tvPopularity);
        this.tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
        this.tvRunTime = itemView.findViewById(R.id.tvRuntime);
        this.tvVoteAvg = itemView.findViewById(R.id.tvVoteAvg);
    }
}
