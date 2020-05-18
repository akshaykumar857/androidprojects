package com.example.akshay.currencyconv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convToDoll(View view)
    {
        EditText nameEditText=(EditText) findViewById(R.id.editText);
        Double d=Double.parseDouble(nameEditText.getText().toString());
        d=d*1.3;
        Toast.makeText(this,String.format("%.2f",d),Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
