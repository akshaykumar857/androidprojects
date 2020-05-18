package com.example.akshay.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase sqLiteDatabase=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("Create table if not exists newusers (name varchar,age INT(3),id Integer Primary Key)");
     //   sqLiteDatabase.execSQL("INSert into newusers(name,age)values('AKsh',56)");
      // sqLiteDatabase.execSQL("INSert into newusers(name,age)values('ANush',30)");
        sqLiteDatabase.execSQL("Delete from newusers where name='ANush'");
        Cursor c=sqLiteDatabase.rawQuery("Select * from newusers",null);
        int nameIndex=c.getColumnIndex("name");
        int ageIndex=c.getColumnIndex("age");
        int idIndex=c.getColumnIndex("id");
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            Log.i("name",c.getString(nameIndex));
            Log.i("age",c.getString(ageIndex));
            Log.i("id",c.getString(idIndex));
            c.moveToNext();
        }


    }
}
