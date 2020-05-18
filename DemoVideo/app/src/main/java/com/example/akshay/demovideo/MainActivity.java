package com.example.akshay.demovideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView videoview=(VideoView)findViewById(R.id.videoView);
        vide



        oview.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.ak);
        MediaController mediacontroller=new MediaController(this);
        mediacontroller.setAnchorView(videoview);
        videoview.setMediaController(mediacontroller);
        videoview.start();

    }
}
