package com.s3.movieflex.adapters;

import android.widget.ImageView;

import com.s3.movieflex.model.MovieModel;

public interface MovieItemClickListener {
    void onMovieClick(MovieModel movie, ImageView movieImageView);
}
