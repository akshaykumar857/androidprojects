package com.example.akshay.interactivitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view)
    {
        EditText nameEditText=(EditText) findViewById(R.id.nameEditText);
        EditText passEditText=(EditText) findViewById(R.id.passEditText);
        Log.i("Info","It worked");
        Log.i("Username",nameEditText.getText().toString());
        Log.i("Password",passEditText.getText().toString());
        Toast.makeText(this,"hi there!!",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
