package com.example.akshay.uiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView txtview;
    public void onShow(View view)
    {

        txtview.setVisibility(View.VISIBLE);
    }
    public void onHide(View view)

    {
        txtview.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         txtview=(TextView)findViewById(R.id.textView);
    }
}
