package com.example.akshay.downloadweb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result="";
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream io = httpURLConnection.getInputStream();
                InputStreamReader ios = new InputStreamReader(io);
                int data = ios.read();

                while(data!=-1)
                {
                    char curr=(char)data;
                    result+=curr;
                    data=ios.read();
                }

                return  result;
            } catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask d=new DownloadTask();
        String result=null;
        try {
             result = d.execute("https" +
                     "://www.zappycode.com").get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.i("Result",result);
        }
}
