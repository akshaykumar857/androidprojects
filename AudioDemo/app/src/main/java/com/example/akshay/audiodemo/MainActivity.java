package com.example.akshay.audiodemo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaplayer;
    AudioManager am;
    public void playBtn(View view)
    {
        mediaplayer.start();
    }
    public void pauseBtn(View view)
    {
        mediaplayer.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        am=(AudioManager) getSystemService(AUDIO_SERVICE);
        int maxv=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currv=am.getStreamVolume(AudioManager.STREAM_MUSIC);
        mediaplayer= MediaPlayer.create(this,R.raw.ashiq);

        SeekBar seekBar=findViewById(R.id.seekBar);
        seekBar.setMax(maxv);
        seekBar.setProgress(currv);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Seekbar changed",Integer.toString(progress));
                am.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final SeekBar seekBar1=(SeekBar)findViewById(R.id.seekBar1);

        seekBar1.setMax(mediaplayer.getDuration());
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Scurbb seekbar moved",Integer.toString(progress));
                mediaplayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                    mediaplayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    mediaplayer.start();
            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar1.setProgress(mediaplayer.getCurrentPosition());
            }
        },0,1000);
    }
}
