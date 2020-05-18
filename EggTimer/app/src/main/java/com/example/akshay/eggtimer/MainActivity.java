package com.example.akshay.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    Button btn;
    TextView timer;
    boolean counter=false;
    CountDownTimer count;
    public void resetTimer()
    {
        timer.setText("00:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        count.cancel();
        btn.setText("Go!");
        counter=false;
    }
    public void onBtn(View view) {
        if (counter) {
            resetTimer();
        } else {
            btn.setText("Stop!");
            seekBar.setEnabled(false);
            counter=true;
           count=new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                public void onTick(long millis) {
                    updateTimer((int) millis / 1000);
                }

                public void onFinish() {
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.buzz);
                    Log.i("Finished", "TImer done");
                    mp.start();
                    resetTimer();
                }
            }.start();
        }
    }
    public void updateTimer(int secleft)
    {
        int  rem=secleft%60;
        int min=secleft/60;
        String sc=Integer.toString(rem);
        if(rem<=9)
        {             sc="0"+Integer.toString(rem);               }
        timer.setText(Integer.toString(min) + ":"+sc);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
      timer=(TextView)findViewById(R.id.textView4);
      btn=(Button)findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
           updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
