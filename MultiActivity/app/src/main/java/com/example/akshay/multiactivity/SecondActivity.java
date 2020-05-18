package com.example.akshay.multiactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    public void onBack(View view)
    {
       // Intent intent=new Intent(this,MainActivity.class);

     //   startActivity(intent);

        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent=getIntent();
       // int age=intent.getIntExtra("age",0);
        //Toast.makeText(this,Integer.toString(age),Toast.LENGTH_LONG).show();
        String name=intent.getStringExtra("name");
        Toast.makeText(this,name,Toast.LENGTH_LONG).show();

    }
}
