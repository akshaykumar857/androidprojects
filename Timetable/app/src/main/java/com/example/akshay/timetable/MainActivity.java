package com.example.akshay.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    public void genrt(int progress)
    {
        ArrayList<Integer> arrayl=new ArrayList<Integer>();
            int count = 10, tab;
            for (int i = 1; i <= count; i++) {
                tab = progress * i;
                arrayl.add(tab);
                Log.i("table", "" + tab);
                Log.i("i", "" + i);
            }


        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayl);
        listview.setAdapter(arrayAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final SeekBar seekbar=(SeekBar)findViewById(R.id.seekbar);
       listview=(ListView)findViewById(R.id.listview);


        seekbar.setMax(20);
        genrt(1);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(progress>0){
                   genrt(progress);
               }
                 else {
                    seekbar.setProgress(1);
                }
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
