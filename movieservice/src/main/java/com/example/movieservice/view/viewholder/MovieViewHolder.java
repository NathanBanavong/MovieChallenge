package com.example.movieservice.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieservice.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivMovie;
    public TextView tvTitle;

    public MovieViewHolder(View itemView) {
        super(itemView);
        this.ivMovie = itemView.findViewById(R.id.ivMovie);
        this.tvTitle = itemView.findViewById(R.id.tvTitle);
    }

}
