package com.s3.movieflex.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.s3.movieflex.R;
import com.s3.movieflex.model.MovieModel;

import java.util.ArrayList;

public class SliderPagerAdapter extends PagerAdapter {
    private final Context mContext;
    private ArrayList<MovieModel> mList = new ArrayList<>();
    private final MovieItemClickListener movieItemClickListener;
    private final String baseURL="https://image.tmdb.org/t/p/original";

    public SliderPagerAdapter(Context mContext, ArrayList<MovieModel> mList, MovieItemClickListener movieItemClickListener) {
        this.mContext = mContext;
        this.mList = mList;
        this.movieItemClickListener = movieItemClickListener;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout =inflater.inflate(R.layout.slide_item,container,false);
        ImageView slideImg =slideLayout.findViewById(R.id.slide_img);
        TextView  slideText =slideLayout.findViewById(R.id.slide_title);
        Glide.with(mContext).load(baseURL + mList.get(position).getPoster_path()).into(slideImg);
        slideText.setText(mList.get(position).getTitle());
        slideImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieItemClickListener.onMovieClick(mList.get(position),slideImg);
            }
        });
        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
