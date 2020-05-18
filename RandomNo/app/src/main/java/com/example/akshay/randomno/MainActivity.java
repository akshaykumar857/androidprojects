package com.example.akshay.randomno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rand=new Random();
    int n;
    public void grno()
    {
        n=rand.nextInt(20)+1;

    }
    public void guessIt(View view)
    {
        EditText editText=(EditText)findViewById(R.id.editText);
        String mess;
        int no=Integer.parseInt(editText.getText().toString());
        if(n==no)
        {
            mess="Correct";
            grno();
        }
        else if(n<no)
        {
            mess="Lower";
        }
        else
        {
            mess="Higher";
        }
        Toast.makeText(this,mess,Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grno();
    }
}
