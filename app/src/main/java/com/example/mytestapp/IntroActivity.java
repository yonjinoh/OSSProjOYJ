package com.example.mytestapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // 스플래시 화면 딜레이 3초
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
            finish();
        }, 3000);
    }
}
