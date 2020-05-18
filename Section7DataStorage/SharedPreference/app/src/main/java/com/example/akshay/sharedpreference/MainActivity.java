package com.example.akshay.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.akshay.sharedpreference", Context.MODE_PRIVATE);
      /*  ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Akshay");
        arrayList.add("Nick");
        arrayList.add("pome");
        try {

            sharedPreferences.edit().putString("friends",ObjectSerializable.serialize(arrayList)).apply();
            Log.i("friends",ObjectSerializable.serialize(arrayList));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
       ArrayList<String> arrayList1=new ArrayList<>();
        try {
            arrayList1=(ArrayList<String>) ObjectSerializable.deserialize(sharedPreferences.getString("friends",ObjectSerializable.serialize(new ArrayList<String>())));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("New friends",arrayList1.toString());
       // sharedPreferences.edit().putString("username","nick").apply();
      //  String username=sharedPreferences.getString("username","");
        //Log.i("username",username);
    }
}
