package com.example.consultants.moviechallenge.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.consultants.moviechallenge.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivMovie;
    public TextView tvTitle;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivMovie = itemView.findViewById(R.id.ivMovie);
        this.tvTitle = itemView.findViewById(R.id.tvTitle);
    }
}
