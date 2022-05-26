package com.s3.movieflex.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.s3.movieflex.R;
import com.s3.movieflex.model.Cast;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {
    // arraylist to hold movies data
    ArrayList<Cast> casts;
    // static   MovieItemClickListener movieItemClickListener;
    private final String baseURL="https://image.tmdb.org/t/p/original";
    private final Context context;

    public CastAdapter(Context context,ArrayList<Cast> casts) {
        this.context=context;
        this.casts = casts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item, parent, false);
        // return our view and the listener on it
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data from array list
        Cast cast = casts.get(position);
        //assign the data to each view element
        Glide.with(context).load(baseURL + cast.getProfile_path()).into(holder.cImage);
    }

    // to get number of elements on the list
    @Override
    public int getItemCount() {
        return casts.size();
    }

    // our inner class that holds the items which we want draw and implements the on click listener on them
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView cImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //make the class see each item that will be hold
            cImage = (ImageView) itemView.findViewById(R.id.img_cast);
        }
    }
}