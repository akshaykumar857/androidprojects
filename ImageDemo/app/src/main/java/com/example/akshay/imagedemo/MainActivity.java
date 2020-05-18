package com.example.akshay.imagedemo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void switchImg(View view)
    {
        Log.i("INfo","Button pressed");
        ImageView image=(ImageView)findViewById(R.id.imageView);
        image.setImageResource(R.drawable.a1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
