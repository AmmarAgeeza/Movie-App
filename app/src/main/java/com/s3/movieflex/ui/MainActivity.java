package com.s3.movieflex.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.s3.movieflex.R;
import com.s3.movieflex.ui.fragments.FavoriteFragment;
import com.s3.movieflex.ui.fragments.HomeFragment;
import com.s3.movieflex.ui.fragments.SearchFragment;

import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        bnv = findViewById(R.id.nav_bar);
        bnv.setOnItemSelectedListener(navMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
    }
    private NavigationBarView.OnItemSelectedListener navMethod = new NavigationBarView.OnItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new HomeFragment();
                    break;
                case R.id.search:
                    fragment = new SearchFragment();
                    break;
                case R.id.favorite:
                    fragment=new FavoriteFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            return true;
        }
    };
}