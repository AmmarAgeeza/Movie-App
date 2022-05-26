package com.s3.movieflex.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.s3.movieflex.R;
import com.s3.movieflex.model.MovieModel;

import java.util.ArrayList;

public class MovieFavAdapter extends RecyclerView.Adapter<MovieFavAdapter.ViewHolder> {
    // arraylist to hold movies data
    static ArrayList<MovieModel> fList;
    //our custom listener te check the item clicked or no
    static MovieItemClickListener movieItemClickListener;
    private final String baseURL = "https://image.tmdb.org/t/p/original";
    private final Context context;

    public MovieFavAdapter(Context context, ArrayList<MovieModel> fList, MovieItemClickListener listener) {
        MovieFavAdapter.fList = fList;
        movieItemClickListener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_recycler_view, parent, false);
        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data from array list
        MovieModel film = fList.get(position);
        //assign the data to each view element
        if (film.getTitle() != null)
            holder.fTitle.setText(film.getTitle());
        else if (film.getName() != null)
            holder.fTitle.setText(film.getName());
        holder.fRate.setText(film.getVote_average() + " / 10 ");
        Log.i("TAG", "onBindViewHolder: rate = " + film.getVote_average());
        Glide.with(context).load(baseURL + film.getPoster_path()).into(holder.fImage);
    }

    // to get number of elements on the list
    @Override
    public int getItemCount() {
        return fList.size();
    }

    // our inner class that holds the items which we want draw and implements the on click listener on them
    public static class ViewHolder extends RecyclerView.ViewHolder {

        //the first item
        private final TextView fTitle;
        private final TextView fRate;

        //the second item
        private final ImageView fImage;
        private final LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //make the class see each item that will be hold
            fTitle = itemView.findViewById(R.id.fav_name);
            fRate = itemView.findViewById(R.id.fav_rate);
            fImage = itemView.findViewById(R.id.fav_img);
            layout = itemView.findViewById(R.id.labeled);
            //apply our listener to final view
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieItemClickListener.onMovieClick(fList.get(getBindingAdapterPosition()), fImage);
                }
            });
            fImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieItemClickListener.onMovieClick(fList.get(getBindingAdapterPosition()), fImage);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieItemClickListener.onMovieClick(fList.get(getBindingAdapterPosition()), fImage);
                }
            });
        }
    }
}