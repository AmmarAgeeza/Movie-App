package com.s3.movieflex.ui.fragments;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s3.movieflex.R;
import com.s3.movieflex.adapters.MovieFavAdapter;
import com.s3.movieflex.adapters.MovieItemClickListener;
import com.s3.movieflex.adapters.retrofit.JSONMovieRespons;
import com.s3.movieflex.adapters.retrofit.RetrofitClient;
import com.s3.movieflex.model.MovieModel;
import com.s3.movieflex.ui.MovieDetailActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment implements MovieItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    EditText searchEditText;
    private String str;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    MovieFavAdapter adapter;
    RecyclerView search_result;
    Spinner spinner;
    ArrayList<MovieModel> movies = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        search_result = view.findViewById(R.id.search_result);
        searchEditText = view.findViewById(R.id.searchEditText);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0)
                    searchMovie(charSequence.toString());
                else
                    movies.clear();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





       /* spinner = view.findViewById(R.id.spin);
        String[] cate = {"Movie", "Tv Show"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cate);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    searchEditText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            if (charSequence.length() != 0)
                                searchMovie(charSequence.toString());
                            else
                                movies.clear();

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });

                } else if (i == 1) {
                    searchEditText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            if (charSequence.length() != 0)
                                searchTvShow(charSequence.toString());
                            else
                                movies.clear();
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });*/


        return view;
    }

    private void searchMovie(String text) {
        RetrofitClient.getRetrofitData().searchMovie(text).enqueue(new Callback<JSONMovieRespons>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<JSONMovieRespons> call, Response<JSONMovieRespons> response) {
                if (response.body().getResults().size() != 0) {
                    movies = response.body().getResults();
                    adapter = new MovieFavAdapter(getContext(), movies, SearchFragment.this);
                    search_result.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    search_result.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<JSONMovieRespons> call, Throwable t) {
                movies.clear();
            }
        });

    }

    /*private void searchTvShow(String text) {
        RetrofitClient.getRetrofitData().searchTvShow(text).enqueue(new Callback<JSONMovieRespons>() {
            @Override
            public void onResponse(Call<JSONMovieRespons> call, Response<JSONMovieRespons> response) {
                if (response.body().getResults().size() != 0) {
                    movies = response.body().getResults();
                    adapter = new MovieFavAdapter(getContext(), movies, SearchFragment.this);
                    search_result.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    search_result.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<JSONMovieRespons> call, Throwable t) {
                movies.clear();
            }
        });
    }*/

    @Override
    public void onMovieClick(MovieModel movie, ImageView movieImageView) {
        if (getActivity() != null) {

            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra("movie", movie);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
            startActivity(intent, options.toBundle());
        }
    }


}