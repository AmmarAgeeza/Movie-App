package com.s3.movieflex.ui.fragments;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.s3.movieflex.R;
import com.s3.movieflex.adapters.MovieItemClickListener;
import com.s3.movieflex.adapters.SliderPagerAdapter;
import com.s3.movieflex.adapters.movieadapters.MovieAdapter2;
import com.s3.movieflex.adapters.movieadapters.MovieAdapter3;
import com.s3.movieflex.adapters.movieadapters.MovieAdapter4;
import com.s3.movieflex.adapters.movieadapters.MovieAdapter5;
import com.s3.movieflex.adapters.movieadapters.MovieAdapter6;
import com.s3.movieflex.adapters.movieadapters.MovieAdapter7;
import com.s3.movieflex.adapters.retrofit.JSONMovieRespons;
import com.s3.movieflex.adapters.retrofit.RetrofitClient;
import com.s3.movieflex.adapters.sqlite.DbController;
import com.s3.movieflex.model.MovieModel;
import com.s3.movieflex.ui.MovieDetailActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements MovieItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    private final String movieUpcomingUrl = "upcoming";
    private final String moviepopularUrl = "popular";
    private final String movieTopRatedUrl = "top_rated";
    private final String movieNowPlayingUrl = "now_playing";
    private final String tvTopRatedUrl = "top_rated";
    private final String tvPopularUrl = "popular";
    private final String tvonAirUrl = "on_the_air";


    ArrayList<MovieModel> lstMovie = new ArrayList<>();
    ArrayList<MovieModel> lstMovieTop = new ArrayList<>();
    ArrayList<MovieModel> lstMoviePopular = new ArrayList<>();
    ArrayList<MovieModel> lstMoviePlaying = new ArrayList<>();
    ArrayList<MovieModel> lstTvTop = new ArrayList<>();
    ArrayList<MovieModel> lstTvPopular = new ArrayList<>();
    ArrayList<MovieModel> lstTvOnAir = new ArrayList<>();
    TabLayout indicators;
    DbController controller;

    RecyclerView moviesRV, moviesTop, moviesPlaying, tvTop, tvPopular, tvOnAir;
    ViewPager sliderPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        indicators = view.findViewById(R.id.indicators);
        sliderPager = view.findViewById(R.id.slider_pager);
        moviesRV = view.findViewById(R.id.Rv_movies);
        moviesTop = view.findViewById(R.id.Rv_movies_top);
        moviesPlaying = view.findViewById(R.id.Rv_movies_playing);
        tvTop = view.findViewById(R.id.Rv_tv_top);
        tvPopular = view.findViewById(R.id.Rv_tv_popular);
        tvOnAir = view.findViewById(R.id.Rv_tv_on_air);
        controller = new DbController(getContext());
        controller.open();

        fillLists();


        return view;
    }

    private void fillLists() {
        upComingMovies();
        popularMovies();
        topRatedMovies();
        nowPlayingMovies();
        topRatedTvShows();
        popularTvShows();
        onTheAirTvShows();
    }


    private void upComingMovies() {
        RetrofitClient.getRetrofitData().getMovies(movieUpcomingUrl).enqueue(new Callback<JSONMovieRespons>() {
            @Override
            public void onResponse(Call<JSONMovieRespons> call, Response<JSONMovieRespons> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lstMovie.addAll(response.body().getResults());
                    SliderPagerAdapter adapter = new SliderPagerAdapter(getContext(), lstMovie, HomeFragment.this);
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(), 3000, 5000);
                    sliderPager.setAdapter(adapter);
                    indicators.setupWithViewPager(sliderPager, true);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<JSONMovieRespons> call, Throwable t) {
                Log.i("TAG", "onFailure: upcoming " + t.getMessage());
            }
        });
    }

    private void popularMovies() {
        RetrofitClient.getRetrofitData().getMovies(moviepopularUrl).enqueue(new Callback<JSONMovieRespons>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<JSONMovieRespons> call, Response<JSONMovieRespons> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lstMoviePopular.addAll(response.body().getResults());
                    MovieAdapter2 adapter1 = new MovieAdapter2(getContext(), lstMoviePopular, HomeFragment.this);
                    moviesRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    moviesRV.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<JSONMovieRespons> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());

            }
        });


    }

    private void topRatedMovies() {
        RetrofitClient.getRetrofitData().getMovies(movieTopRatedUrl).enqueue(new Callback<JSONMovieRespons>() {
            @Override
            public void onResponse(Call<JSONMovieRespons> call, Response<JSONMovieRespons> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lstMovieTop.addAll(response.body().getResults());
                    MovieAdapter3 adapter2 = new MovieAdapter3(getContext(), lstMovieTop, HomeFragment.this::onMovieClick);
                    moviesTop.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    moviesTop.setAdapter(adapter2);
                }

            }

            @Override
            public void onFailure(Call<JSONMovieRespons> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());

            }
        });
    }

    private void nowPlayingMovies() {
        RetrofitClient.getRetrofitData().getMovies(movieNowPlayingUrl).enqueue(new Callback<JSONMovieRespons>() {
            @Override
            public void onResponse(Call<JSONMovieRespons> call, Response<JSONMovieRespons> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lstMoviePlaying.addAll(response.body().getResults());
                    MovieAdapter4 adapter3 = new MovieAdapter4(getContext(), lstMoviePlaying, HomeFragment.this::onMovieClick);
                    moviesPlaying.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    moviesPlaying.setAdapter(adapter3);
                }
            }

            @Override
            public void onFailure(Call<JSONMovieRespons> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());

            }
        });
    }


    private void topRatedTvShows() {
        RetrofitClient.getRetrofitData().getTvShows(tvTopRatedUrl).enqueue(new Callback<JSONMovieRespons>() {
            @Override
            public void onResponse(Call<JSONMovieRespons> call, Response<JSONMovieRespons> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lstTvTop.addAll(response.body().getResults());
                    MovieAdapter5 adapter4 = new MovieAdapter5(getContext(), lstTvTop, HomeFragment.this);
                    tvTop.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    tvTop.setAdapter(adapter4);
                }
            }

            @Override
            public void onFailure(Call<JSONMovieRespons> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());

            }
        });

    }

    private void popularTvShows() {

        RetrofitClient.getRetrofitData().getTvShows(tvPopularUrl).enqueue(new Callback<JSONMovieRespons>() {
            @Override
            public void onResponse(Call<JSONMovieRespons> call, Response<JSONMovieRespons> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lstTvPopular.addAll(response.body().getResults());
                    MovieAdapter6 adapter5 = new MovieAdapter6(getContext(), lstTvPopular, HomeFragment.this);
                    tvPopular.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    tvPopular.setAdapter(adapter5);
                }

            }

            @Override
            public void onFailure(Call<JSONMovieRespons> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());

            }
        });


    }

    private void onTheAirTvShows() {
        RetrofitClient.getRetrofitData().getTvShows(tvonAirUrl).enqueue(new Callback<JSONMovieRespons>() {
            @Override
            public void onResponse(Call<JSONMovieRespons> call, Response<JSONMovieRespons> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lstTvOnAir.addAll(response.body().getResults());
                    MovieAdapter7 adapter6 = new MovieAdapter7(getContext(), lstTvOnAir, HomeFragment.this);
                    tvOnAir.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    tvOnAir.setAdapter(adapter6);
                }
            }

            @Override
            public void onFailure(Call<JSONMovieRespons> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());

            }
        });


    }


    @Override
    public void onMovieClick(MovieModel movie, ImageView movieImageView) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        Log.i("TAG", "onFailure: " + movie.getId());
        Log.i("TAG", "onMovieClick: " + movie.getTitle());
        intent.putExtra("type", "movie");
        intent.putExtra("movie", movie);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }

    public class SliderTimer extends TimerTask {
        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (sliderPager.getCurrentItem() < lstMovie.size() - 1)
                            sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                        else
                            sliderPager.setCurrentItem(0);
                    }
                });
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        controller.close();
    }
}