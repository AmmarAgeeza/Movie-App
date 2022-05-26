package com.s3.movieflex.model;

import java.io.Serializable;

public class MovieModel implements Serializable {
    private int id;
    private String title;
    private String name;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private String first_air_date;
    private float vote_average;

    public MovieModel(int id, String title, String name, String overview, String poster_path, String backdrop_path,
                      String release_date, String first_air_date, float vote_average) {

        this.id = id;
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.name = name;
        this.first_air_date = first_air_date;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }
}
