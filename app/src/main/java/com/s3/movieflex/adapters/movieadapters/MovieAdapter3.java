package com.s3.movieflex.adapters.movieadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.s3.movieflex.R;
import com.s3.movieflex.adapters.MovieItemClickListener;
import com.s3.movieflex.model.MovieModel;

import java.util.ArrayList;

public class MovieAdapter3 extends RecyclerView.Adapter<MovieAdapter3.ViewHolder> {
    // arraylist to hold movies data
    static ArrayList<MovieModel> fList;
    //our custom listener te check the item clicked or no
    static MovieItemClickListener movieItemClickListener;
    private final String baseURL = "https://image.tmdb.org/t/p/original";
    private final Context context;

    public MovieAdapter3(Context context, ArrayList<MovieModel> fList, MovieItemClickListener listener) {
        MovieAdapter3.fList = fList;
        movieItemClickListener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        // return our view and the listener on it
        return new ViewHolder(view);
    }

    // take the return value of above function as argument and get the position of it in recycler view
    // Note because of the recycler view indexing and Array list starts at zero index
    // then the position of any view on recycler view is same in array lis
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data from array list
        MovieModel film = fList.get(position);
        //assign the data to each view element
        if (film.getTitle() != null)
            holder.fTitle.setText(film.getTitle());
        else
            holder.fTitle.setText(film.getName());

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
        //the second item
        private final ImageView fImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //make the class see each item that will be hold
            fTitle = itemView.findViewById(R.id.film_title);
            fImage = itemView.findViewById(R.id.film_img);
            //apply our listener to final view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieItemClickListener.onMovieClick(fList.get(getBindingAdapterPosition()), fImage);
                }
            });
        }
    }
}
