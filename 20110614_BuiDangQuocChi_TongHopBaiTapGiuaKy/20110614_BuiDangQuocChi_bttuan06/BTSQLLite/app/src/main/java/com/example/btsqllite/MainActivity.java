package com.example.btsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.btsqllite.adapter.NotesAdapter;
import com.example.btsqllite.model.NotesModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // khai báo biến toàn cục
    DataBaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // goi ham databaseSQLite
        InitDatabaseSQLite();
        // createDatabaseSQLite();
        databaseSQLite();

        listView = (ListView) findViewById(R.id.listView1);
        arrayList = new ArrayList<>();
        adapter = new NotesAdapter(this,R.layout.row_notes,arrayList);
        listView.setAdapter(adapter);
    }

    private void InitDatabaseSQLite() {
    }

    private void createDatabaseSQLite() {
        // them du lieu vao bang
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite 1')");
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite 2')");
    }

    private void databaseSQLite() {
        // lay du lieu
        Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");
        while(cursor.moveToNext()) {
            String name = cursor.getString(1);
            int id = cursor.getInt(0);
            arrayList.add(new NotesModel(id,name));
        }
        adapter.notifyDataSetChanged();
    }
}