package com.example.akshay.multiactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public void onNext(View view)
    {
        //Intent intent=new Intent(this,SecondActivity.class);
      //  intent.putExtra("age",20);
        //startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView) findViewById(R.id.listView);
        final ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.add("AKshay");
        arrayList.add("Ajay"); arrayList.add("ANush"); arrayList.add("AJa");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
                 intent.putExtra("name",arrayList.get(position));
                 startActivity(intent);
            }
        });



    }
}
