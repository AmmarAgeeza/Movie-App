package com.s3.movieflex.ui.fragments;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s3.movieflex.R;
import com.s3.movieflex.adapters.MovieFavAdapter;
import com.s3.movieflex.adapters.MovieItemClickListener;
import com.s3.movieflex.adapters.sqlite.DbController;
import com.s3.movieflex.model.MovieModel;
import com.s3.movieflex.ui.MovieDetailActivity;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment implements MovieItemClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
    ArrayList<MovieModel> film = new ArrayList<>();
    RecyclerView favoriteMovies;
    DbController controller;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        //test
        controller = new DbController(getContext());
        controller.open();

        //favorite RecyclerView adapter and arrayList

        film.clear();
        film = controller.selectAllMovie();
        adapter = new MovieFavAdapter(getContext(), film, FavoriteFragment.this);
        favoriteMovies = view.findViewById(R.id.fav);
        favoriteMovies.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));
        favoriteMovies.setAdapter(adapter);
        return view;
    }

    @Override
    public void onMovieClick(MovieModel movie, ImageView movieImageView) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra("movie", movie);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                    movieImageView, "sharedName");
            startActivity(intent, options.toBundle());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        controller.close();
    }


}