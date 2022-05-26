package com.s3.movieflex.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.s3.movieflex.R;
import com.s3.movieflex.adapters.CastAdapter;
import com.s3.movieflex.adapters.retrofit.JSONCastRespons;
import com.s3.movieflex.adapters.retrofit.JSONTrailRespons;
import com.s3.movieflex.adapters.retrofit.RetrofitClient;
import com.s3.movieflex.adapters.sqlite.DbController;
import com.s3.movieflex.model.Cast;
import com.s3.movieflex.model.MovieModel;
import com.s3.movieflex.model.Trail;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {
    ImageView movieImg, movieCover;
    TextView movieTitle, movieDesc;
    RecyclerView rvCast;
    ArrayList<Cast> cast = new ArrayList<>();
    CastAdapter castAdapter;
    MovieModel movieDetail;
    ImageButton favorite;
    String showLink;
    DbController controller;
    FloatingActionButton openTrail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        iniView();
        iniData();
    }

    private void iniView() {
        rvCast = findViewById(R.id.rv_cast);

        movieImg = findViewById(R.id.detail_movie_img);
        movieCover = findViewById(R.id.detail_movie_cover);
        movieTitle = findViewById(R.id.detail_movie_title);
        movieDesc = findViewById(R.id.detail_movie_desc);
        favorite = findViewById(R.id.fav_btn);
        openTrail = findViewById(R.id.trial);

    }


    private void iniData() {
        controller = new DbController(getApplicationContext());
        controller.open();
        if (getIntent() != null) {
            movieDetail = (MovieModel) getIntent().getExtras().getSerializable("movie");
            boolean is = controller.selectMovie(movieDetail.getId());
            if (is) {
                favorite.setBackgroundResource(R.drawable.ic_baseline_favorite_red);
                //movieDetail.setMovieId(-1);
            } else {
                favorite.setBackgroundResource(R.drawable.ic_baseline_favorite);
            }
            String baseURL = "https://image.tmdb.org/t/p/original";
            Glide.with(this).load(baseURL + movieDetail.getPoster_path()).into(movieImg);
            Glide.with(this).load(baseURL + movieDetail.getBackdrop_path()).into(movieCover);
            if (movieDetail.getTitle() == null)
                movieTitle.setText(movieDetail.getName());
            else
                movieTitle.setText(movieDetail.getTitle());
            movieDesc.setText(movieDetail.getOverview());
            movieCover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_photo));
            Objects.requireNonNull(getSupportActionBar()).setTitle(movieDetail.getTitle());
            if (movieDetail.getTitle() != null) {
                RetrofitClient.getRetrofitData().getMovieCast(movieDetail.getId()).enqueue(new Callback<JSONCastRespons>() {
                    @Override
                    public void onResponse(Call<JSONCastRespons> call, Response<JSONCastRespons> response) {
                        cast = response.body().getCast();
                        castAdapter = new CastAdapter(getApplicationContext(), cast);
                        rvCast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        rvCast.setAdapter(castAdapter);

                    }

                    @Override
                    public void onFailure(Call<JSONCastRespons> call, Throwable t) {
                        Log.i("TAG", "onFailure: " + t.getMessage());
                    }
                });
            } else {
                RetrofitClient.getRetrofitData().getTvShowsCast(movieDetail.getId()).enqueue(new Callback<JSONCastRespons>() {
                    @Override
                    public void onResponse(Call<JSONCastRespons> call, Response<JSONCastRespons> response) {

                        cast = response.body().getCast();
                        castAdapter = new CastAdapter(getApplicationContext(), cast);
                        rvCast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        rvCast.setAdapter(castAdapter);

                    }

                    @Override
                    public void onFailure(Call<JSONCastRespons> call, Throwable t) {
                        Log.i("TAG", "onFailure: " + t.getMessage());
                    }
                });
            }

            if (movieDetail.getTitle() != null) {

                RetrofitClient.getRetrofitData().getMovieTrail(movieDetail.getId()).enqueue(new Callback<JSONTrailRespons>() {
                    @Override
                    public void onResponse(@NonNull Call<JSONTrailRespons> call, Response<JSONTrailRespons> response) {

                        if (response.body().getResults().size() != 0) {
                            Trail getLink = response.body().getResults().get(0);
                            showLink = "https://www.youtube.com/watch?v=" + getLink.getKey();
                        }
                    }

                    @Override
                    public void onFailure(Call<JSONTrailRespons> call, Throwable t) {
                        Log.i("TAG", "onFailure: movie id " + movieDetail.getId());
                    }
                });
            } else {
                RetrofitClient.getRetrofitData().getTvShowTrail(movieDetail.getId()).enqueue(new Callback<JSONTrailRespons>() {
                    @Override
                    public void onResponse(Call<JSONTrailRespons> call, Response<JSONTrailRespons> response) {
                        if (response.body().getResults().size() != 0) {

                            Trail getLink = response.body().getResults().get(0);
                            showLink = "https://www.youtube.com/watch?v=" + getLink.getKey();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<JSONTrailRespons> call, @NonNull Throwable t) {
                        Log.i("TAG", "onFailure:movie id " + movieDetail.getId());
                    }
                });


            }


            openTrail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (showLink != null) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(showLink));
                        startActivity(intent);
                    } else
                        Toast.makeText(getApplicationContext(), "no trail exist", Toast.LENGTH_SHORT).show();
                }
            });

            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (is) {
                        favorite.setBackgroundResource(R.drawable.ic_baseline_favorite);
                        controller.delete(movieDetail.getId());
                        //movieDetail.setMovieId(-1);
                        Toast.makeText(MovieDetailActivity.this.getApplicationContext(), "remove", Toast.LENGTH_SHORT).show();
                    } else {
                        favorite.setBackgroundResource(R.drawable.ic_baseline_favorite_red);
                        movieDetail.setId(controller.addMovie(movieDetail));
                        Toast.makeText(MovieDetailActivity.this.getApplicationContext(), "favorite", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.close();
    }
}