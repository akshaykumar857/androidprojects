package com.example.akshay.numbershape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int number;
    public boolean isSquare()
    {
        double s=Math.sqrt(number);
        if(s==Math.floor(s))
        {
            return true;
        }
        else
            return false;
    }
    public boolean isTraiangle()
    {
        int i=1,tri=1;
        while(tri<number)
        {
            i++;
            tri=tri+i;

        }
        if(tri==number)
        return true;
        else
        return false;


    }
    public void checkIt(View view)
    {
        EditText editText=(EditText)findViewById(R.id.editText2);
        number=Integer.parseInt(editText.getText().toString());
        String mess;
        if (isSquare() && isTraiangle())
            mess="NUmber is both square and triangle";
        else if(isSquare())
        {
            mess="Number is square";
        }
        else if(isTraiangle())
        {
            mess="Number is triangle";
        }
        else
        {
            mess="Not square or triangle";
        }
        Toast.makeText(this,mess,Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
