package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.surface_view);
        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse("https://developers.google.com/training/images/tacoma_narrows.mp4");

        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        videoView.setVideoURI(uri);
        this.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        );
        videoView.start();
    }
}
