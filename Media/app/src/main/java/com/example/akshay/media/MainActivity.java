package com.example.akshay.media;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
   // boolean kwishow=true;

    public void fade(View view)
    {
     //   ImageView imageView=(ImageView)findViewById(R.id.imageView);
      //  ImageView imageView1=(ImageView)findViewById(R.id.imageView1);
       // imageView.animate().translationYBy(1000).setDuration(2000);
     //   imageView.animate().rotation(1000).alpha(0).setDuration(1000);
       // imageView.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000);
     /*   if(kwishow) {
            kwishow=false;
            imageView.animate().alpha(0).setDuration(2000);
            imageView1.animate().alpha(1).setDuration(2000);
        }
        else
        {
            kwishow=true;
            imageView.animate().alpha(1).setDuration(2000);
            imageView1.animate().alpha(0).setDuration(2000);
        }*/
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setX(-1000);
        imageView.animate().translationXBy(1000).rotation(3600).setDuration(2000);

    }
}
