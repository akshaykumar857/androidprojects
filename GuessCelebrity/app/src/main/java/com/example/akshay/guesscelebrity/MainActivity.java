package com.example.akshay.guesscelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> celebURL;
    ArrayList<String> celebNames;
    ImageView imageView;
    Button button;
    Button button1;
    int chooseceleb;
    Button button2;
    Button button3;
    String[] answers;
    int wrongceleb,correctceleb;
    public void chooseAns(View view)
    {
if(view.getTag().toString().equals(Integer.toString(correctceleb)))
{
    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
}
else {
    Toast.makeText(getApplicationContext(), "Wrong!!It was" + celebNames.get(chooseceleb), Toast.LENGTH_SHORT).show();
}
    nextQuestion();
    }
    public void nextQuestion()
    {
        Bitmap bm;
        String result=null;
        try {
            ImageLoader il=new ImageLoader();
            DownloadTask d = new DownloadTask();
            result = d.execute("http://www.posh24.se/kandisar").get();
            //    Log.i("res",result);
            String[] splitRes=result.split("<div class=\"listedArticles\">");
            Pattern p=Pattern.compile("img src=\"(.*?)\"");
            celebURL=new ArrayList<String>();
            celebNames=new ArrayList<String>();
            //   Log.i("res", Arrays.toString(splitRes));
            Matcher m=p.matcher(splitRes[0]);
            while(m.find())
            {
                celebURL.add(m.group(1));
            }
            for(String a:celebURL)
            {
                Log.i("url",a);
            }
            p=Pattern.compile("alt=\"(.*?)\"");
            m=p.matcher(splitRes[0]);
            while(m.find())
            {
                celebNames.add(m.group(1));
            }
            for(String a:celebNames)
            {
                Log.i("name",a);
            }
            Log.i("urlsize",Integer.toString(celebURL.size()));
            Log.i("namesize",Integer.toString(celebNames.size()));
            Random rand=new Random();
            answers=new String[4];
           chooseceleb=rand.nextInt(celebURL.size());
            bm=il.execute(celebURL.get(chooseceleb)).get();
            imageView.setImageBitmap(bm);

            correctceleb=rand.nextInt(4);
            for(int i=0;i<4;i++)
            {
                if(i==correctceleb)
                {
                    answers[i]=celebNames.get(chooseceleb);
                    Log.i("correct",answers[i]);
                }
                else
                {
                    wrongceleb=rand.nextInt(celebURL.size());
                    while(wrongceleb==chooseceleb)
                    {
                        wrongceleb=rand.nextInt(celebURL.size());

                    }
                    answers[i]=celebNames.get(wrongceleb);
                }
            }
            Log.i("answers",Arrays.toString(answers));
            button.setText(answers[0]);
            button1.setText(answers[1]);   button2.setText(answers[2]);   button3.setText(answers[3]);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public class ImageLoader extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            try
            {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                InputStream io=httpURLConnection.getInputStream();
                Bitmap bm= BitmapFactory.decodeStream(io);
                return  bm;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
    }
    public class DownloadTask extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... strings) {
            try {

                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream io = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(io);
                int data = isr.read();
                String result="";
                while (data != -1)
                {
                    char curr=(char)data;
                    result+=curr;
                    data=isr.read();
                }
                return result;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return "Failed";
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
        button=(Button)findViewById(R.id.button);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);

      nextQuestion();
    }
}
