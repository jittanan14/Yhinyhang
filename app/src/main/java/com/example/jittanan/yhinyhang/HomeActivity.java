package com.example.jittanan.yhinyhang;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class HomeActivity extends AppCompatActivity {
    CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        cdt = new CountDownTimer(   1000, 50) {
            public void onTick(long millisUntilFinished) {
                String strTime = String.format("%.1f", (double)millisUntilFinished / 1000);
            }

            public void onFinish() {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        }.start();
    }
}
