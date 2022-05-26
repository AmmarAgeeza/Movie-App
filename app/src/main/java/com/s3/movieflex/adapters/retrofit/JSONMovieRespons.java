package com.s3.movieflex.adapters.retrofit;

import com.s3.movieflex.model.MovieModel;

import java.util.ArrayList;

public class JSONMovieRespons {
    ArrayList<MovieModel> results;

    public ArrayList<MovieModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieModel> results) {
        this.results = results;
    }
}
