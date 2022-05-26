package com.s3.movieflex.adapters.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDBApi {
    String apikey = "452671fab9e5a7eb0349b6139855d282";

    @GET("movie/{cate}?api_key=" + apikey)
    Call<JSONMovieRespons> getMovies(@Path("cate") String cate);

    @GET("tv/{cate}?api_key=" + apikey)
    Call<JSONMovieRespons> getTvShows(@Path("cate") String cate);

    //movie/550/credits?api_key=452671fab9e5a7eb0349b6139855d282
    @GET("movie/{id}/credits?api_key=" + apikey)
    Call<JSONCastRespons> getMovieCast(@Path("id") int id);

    @GET("tv/{id}/credits?api_key=" + apikey)
    Call<JSONCastRespons> getTvShowsCast(@Path("id") int id);

    //movie/550/videos?api_key=452671fab9e5a7eb0349b6139855d282
    @GET("movie/{id}/videos?api_key=" + apikey)
    Call<JSONTrailRespons> getMovieTrail(@Path("id") int id);

    @GET("tv/{id}/videos?api_key=" + apikey)
    Call<JSONTrailRespons> getTvShowTrail(@Path("id") int id);

    //search/movie?api_key=452671fab9e5a7eb0349b6139855d282&query=
    @GET("search/movie?api_key=" + apikey + "&query=")
    Call<JSONMovieRespons> searchMovie(@Query("query") String name);

    @GET("search/tv?api_key=" + apikey + "&query=")
    Call<JSONMovieRespons> searchTvShow(@Query("query") String name);
}
