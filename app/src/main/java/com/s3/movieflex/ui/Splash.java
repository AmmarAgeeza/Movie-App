package com.s3.movieflex.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.s3.movieflex.R;

public class Splash extends AppCompatActivity {
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Login_Activity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}