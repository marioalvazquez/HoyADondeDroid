package com.example.hoyadonde;

import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.view.WindowManager;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

public class splash_activity extends AppCompatActivity {

    private final int DURACION_SPLASH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_activity);
        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(splash_activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
