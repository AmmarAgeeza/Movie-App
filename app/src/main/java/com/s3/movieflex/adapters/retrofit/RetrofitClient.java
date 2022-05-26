package com.s3.movieflex.adapters.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String baseUrl = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    public static MovieDBApi getRetrofitData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MovieDBApi.class);
    }
}
