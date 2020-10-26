package com.andrew.bakingapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.andrew.bakingapp.ui.main.MainActivity;
import com.andrew.bakingapp.R;
import com.andrew.bakingapp.utils.Constant;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> startActivity(new Intent(SplashActivity.this, MainActivity.class))
                , Constant.SPLASH_DELAY);
    }
}