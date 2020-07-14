package com.example.minitiktok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.os.CountDownTimer;

import com.example.minitiktok.R;
import com.example.minitiktok.tool.BaseActivity;

public class SplashScreen extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void init() {
        setFullScreen();

        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}