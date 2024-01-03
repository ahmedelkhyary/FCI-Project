package com.collegemangement.fci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class videoplyer extends AppCompatActivity {
    VideoView videoView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplyer);

        videoView = findViewById(R.id.video);

        Intent intent = getIntent();
        String path =  intent.getStringExtra("video");
        videoView.setVideoPath(path);
        videoView.start();
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }
}
