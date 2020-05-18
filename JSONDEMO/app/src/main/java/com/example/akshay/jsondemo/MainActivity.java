package com.example.akshay.jsondemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String result="";
            try
            {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                InputStream io=httpURLConnection.getInputStream();
                InputStreamReader ios=new InputStreamReader(io);
                int data=ios.read();
                while(data!=-1)
                {
                    char curr=(char)data;
                    result+=curr;
                    data=ios.read();
                                    }
                return  result;
            }catch(Exception e)
            {
                e.printStackTrace();
return "Failure";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("json",s);
            try {
                JSONObject jsonObject=new JSONObject(s);
                String weatherInfo=jsonObject.getString("weather");
                Log.i("weather content",weatherInfo);
                JSONArray arr=new JSONArray(weatherInfo);
            for
                    (int i=0;i<arr.length();i++)
            {
                JSONObject jsonpart=arr.getJSONObject(i);
                Log.i("main",jsonpart.getString("main"));
                Log.i("desc",jsonpart.getString("description"));
            }
            }

                            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask d=new DownloadTask();
        String result="";
        try {
            result = d.execute("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02").get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
