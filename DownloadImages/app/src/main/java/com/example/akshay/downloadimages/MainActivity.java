package com.example.akshay.downloadimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public  void download(View view) {
        ImageDownloader id = new ImageDownloader();
        Bitmap bm;
    try {
        bm = id.execute("https://upload.wikimedia.org/wikipedia/en/0/02/Homer_Simpson_2006px.png").get();
        imageView.setImageBitmap(bm);
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }

    }
    public class ImageDownloader extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.connect();
                InputStream in=httpURLConnection.getInputStream();
                Bitmap bm= BitmapFactory.decodeStream(in);
                return bm;
            }
            catch(Exception e)
            {
                e.printStackTrace();
return  null;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
    }
}
