
package com.example.tuan5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import vn.iostar.lisstview.MonHoc;
import vn.iostar.lisstview.MonhocAdapter;

public class MainActivity extends AppCompatActivity {

    //khai báo
    ListView listView;
    ArrayList<MonHoc> arrayList;
    MonhocAdapter adapter;
    Button button;
    View view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btnbt2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });

        AnhXa();
        //Tạo Adapter
        adapter = new MonhocAdapter(MainActivity.this,R.layout.row_monhoc,arrayList);
        //truyền dữ liệu từ adapter ra listview
        listView.setAdapter(adapter);



    }
    private  void AnhXa() {
       listView = (ListView) findViewById(R.id.listview1);
      /* editText1 = (EditText) findViewById(R.id.editText1);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);*/
//Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java","hoc java",R.drawable.java));
        arrayList.add(new MonHoc("C#","C# ",R.drawable.c));
        arrayList.add(new MonHoc("PHP","PHP",R.drawable.php));
      arrayList.add(new MonHoc("Kotlin","Kotlin",R.drawable.kotlin));
        arrayList.add(new MonHoc("Dart","Dart",R.drawable.dart));
    }
}