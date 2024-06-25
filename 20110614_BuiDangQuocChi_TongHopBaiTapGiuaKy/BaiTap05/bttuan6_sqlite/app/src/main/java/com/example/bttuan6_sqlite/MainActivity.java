package com.example.bttuan6_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bttuan6_sqlite.controller.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitDatabaseSQLite();
        databaseSQLite();
    }

    private void databaseSQLite() {

        //laays du lieu
        Cursor cursor = databaseHandler.GetData("select * from Notes");
        while (cursor.moveToNext()){
            String name =cursor.getString(1);
            Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        }
    }

    private void InitDatabaseSQLite() {
        databaseHandler = new DatabaseHandler(this,"notes.sqlite",null,1 );
        databaseHandler.QueryData("Create Table If not exists Notes(id integer primary key autoincrement,NameNotes varchar(200))");

    }

}