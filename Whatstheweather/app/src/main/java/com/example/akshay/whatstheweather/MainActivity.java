package com.example.akshay.whatstheweather;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    TextView resultTextView;
    public void getWeather(View view)
    {
        EditText editText=(EditText)findViewById(R.id.editText);
        resultTextView=(TextView)findViewById(R.id.textView2);

        DownloadTask d=new DownloadTask();
        String result="";
        try {
            String encodeCityName= URLEncoder.encode(editText.getText().toString(),"UTF-8");
            result = d.execute("https://openweathermap.org/data/2.5/weather?q="+editText.getText().toString()+"&appid=439d4b804bc8187953eb36d2a8c26a02").get();
            resultTextView.setText(result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"COuld not find weather", Toast.LENGTH_SHORT).show();
        }
        InputMethodManager mgr=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(editText.getWindowToken(),0);
    }
    public class DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream io = httpURLConnection.getInputStream();
                InputStreamReader ios = new InputStreamReader(io);
                int data = ios.read();
                while (data != -1) {
                    char curr = (char) data;
                    result += curr;
                    data = ios.read();
                }
                return result;
            } catch (Exception e) {
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
                String message="";
                for
                        (int i=0;i<arr.length();i++)
                {
                    JSONObject jsonpart=arr.getJSONObject(i);
                   String main=jsonpart.getString("main");
                   String desc=jsonpart.getString("description");
                if(!main.equals("") && !desc.equals(""))
                {
                    message+=main+":"+desc+"\r\n";
                }

                }
            if(!message.equals(""))
            {
                resultTextView.setText(message);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Couldnot find weather", Toast.LENGTH_SHORT).show();
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

    }
}
