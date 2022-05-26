package com.s3.movieflex.adapters.retrofit;

import com.s3.movieflex.model.Cast;

import java.util.ArrayList;

public class JSONCastRespons {
    ArrayList<Cast> cast;

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }
}
