package com.example.mytestapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // 스플래시 화면 딜레이 3초
        Handler handler = new Handler();
        handler.postDelayed(new SplashHandler(), 3000);
    }

    private class SplashHandler implements Runnable {
        public void run() {
            // 딜레이 후 MainActivity 시작
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
            // IntroActivity 종료하여 뒤로가기 버튼을 눌러 복귀할 수 없도록 함
            finish();
        }
    }
}




