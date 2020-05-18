package com.example.akshay.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

public class NoteActivity extends AppCompatActivity {
EditText editText;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editText=(EditText)findViewById(R.id.editText);
        Intent intent=getIntent();
       a= intent.getIntExtra("noteid",-1);
       if(a!=-1)
       {
       editText.setText(MainActivity.arrayList.get(a));
    }
    else
       {
            MainActivity.arrayList.add("");
            a= MainActivity.arrayList.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
       }
       editText
               .addTextChangedListener(new TextWatcher() {
                   @Override
                   public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                   }

                   @Override
                   public void onTextChanged(CharSequence s, int start, int before, int count) {
                    MainActivity.arrayList.set(a,String.valueOf(s));
                    MainActivity.arrayAdapter.notifyDataSetChanged();
                       SharedPreferences sharedPreferences=getSharedPreferences("com.example.akshay.notesapp", Context.MODE_PRIVATE);
                       HashSet<String> set=new HashSet<>(MainActivity.arrayList);
                       sharedPreferences.edit().putStringSet("notes",set).apply();
                   }

                   @Override
                   public void afterTextChanged(Editable s) {

                   }
               });
    }
}
