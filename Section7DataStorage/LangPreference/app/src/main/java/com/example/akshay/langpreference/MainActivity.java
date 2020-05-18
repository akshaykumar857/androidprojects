package com.example.akshay.langpreference;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SharedPreferences sharedPreferences;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.english:
                sharedPreferences.edit().putString("lang","English").apply();
                textView.setText("English is selected");
                return true;
            case R.id.spanish:
                sharedPreferences.edit().putString("lang","Spanish").apply();
                textView.setText("Spanish is selected");
                return true;
                default:return  false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences("com.example.akshay.langpreferenc", Context.MODE_PRIVATE);
        String lang=sharedPreferences.getString("lang","error");
        if(lang.equals("error")) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Language Selction")
                    .setMessage("Select any Lang")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sharedPreferences.edit().putString("lang", "English").apply();
                            textView.setText("English is selected");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sharedPreferences.edit().putString("lang", "Spanish").apply();
                            textView.setText("Spanish is selected");
                        }
                    }).show();
        }
        else
        {
            textView.setText(lang+"is selected");
        }
    }
}
