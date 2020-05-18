package com.example.akshay.newsreader;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
ArrayList<String> news=new ArrayList<>();
    ArrayAdapter arrayAdapter;
    ArrayList<String> contents=new ArrayList<>();
SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);

        sqLiteDatabase=this.openOrCreateDatabase("aricles",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("Create table if not exists articles(id Integer Primary key,artid Integer,title varchar,content varchar)");

        DownloadTask dt=new DownloadTask();

   //   dt.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,news);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("content",contents.get(position));
                startActivity(intent);
            }
        });
      updatelistView();

    }
    public void updatelistView()
    {
        Cursor c=sqLiteDatabase.rawQuery("Select * from articles",null);
        int contentindex=c.getColumnIndex("content");
        int titleindex=c.getColumnIndex("title");
        if(c.moveToFirst())
        {
            news.clear();
            contents.clear();
                   }
                   do{
            Log.i("title",c.getString(titleindex));
                       Log.i("contents",c.getString(contentindex));
            news.add(c.getString(titleindex));
            contents.add(c.getString(contentindex));
                   }while(c.moveToNext());
        arrayAdapter.notifyDataSetChanged();
    }
    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url=new URL(strings[0]);
                String result="";
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream is=httpURLConnection.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                int data=isr.read();

                while(data!=-1)
                {
                    char ch=(char)data;
                    result+=ch;
                    data=isr.read();
                }
                Log.i("result",result);
                JSONArray jsonArray=new JSONArray(result);
                int noItm=20;
                if(jsonArray.length()<20) {
                noItm=jsonArray.length();
                }
              sqLiteDatabase.execSQL("Delete from articles");
                for(int i=0;i<noItm;i++)
                {
                    String artid=jsonArray.getString(i);
                    url=new URL(" https://hacker-news.firebaseio.com/v0/item/"+artid+".json?print=pretty");
                     httpURLConnection=(HttpURLConnection)url.openConnection();
                     is=httpURLConnection.getInputStream();
                   isr=new InputStreamReader(is);
                    data=isr.read();
                    String artinfo="";
                    while(data!=-1)
                    {
                        char ch=(char)data;
                        artinfo+=ch;
                        data=isr.read();
                    }
                    Log.i("artinfo",artid+"\n"+artinfo);
                    JSONObject jsonObject=new JSONObject(artinfo);
                    if(!jsonObject.isNull("title")&& !jsonObject.isNull("url"))
                    {
                        String title=jsonObject.getString("title");
                        String urls=jsonObject.getString("url");
                    url=new URL(urls);
                    httpURLConnection=(HttpURLConnection)url.openConnection();
                    is=httpURLConnection.getInputStream();
                    isr=new InputStreamReader(is);
                    data=isr.read();
                    String content="";
                    while(data!=-1)
                    {
                        char ch=(char)data;
                        content+=ch;
                        data=isr.read();
                    }
                    Log.i("content",content);
                    String sql="Insert into articles(artid,title,content)values(?,?,?)";
                        SQLiteStatement sqLiteStatement=sqLiteDatabase.compileStatement(sql);
                        sqLiteStatement.bindString(1,artid);
                        sqLiteStatement.bindString(2,title);
                        sqLiteStatement.bindString(3,content);
                        sqLiteStatement.execute();

                    }
                }
return  result;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            updatelistView();
        }
    }
}
