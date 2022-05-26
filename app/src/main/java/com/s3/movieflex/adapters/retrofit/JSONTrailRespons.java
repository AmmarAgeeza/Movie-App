package com.s3.movieflex.adapters.retrofit;

import com.s3.movieflex.model.Trail;

import java.util.ArrayList;

public class JSONTrailRespons {
    ArrayList<Trail> results;

    public ArrayList<Trail> getResults() {
        return results;
    }

    public void setResults(ArrayList<Trail> results) {
        this.results = results;
    }
}
