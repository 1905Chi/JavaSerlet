package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class Auto_Backgroud_Load extends AppCompatActivity {

    private int current_color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_backgroud_load);
    }

    // BÀI TẬP 1: TỰ ĐỘNG THAY ĐỔI NÊN CỦA APP (BACKGROUND TẠO NGẪU NHIÊN) KHI LOAD LẠI TRANG, DÙNG ONSTART,
    //@SuppressLint("ResourceType")
    @Override
    protected void onStart() {
        super.onStart();

        ConstraintLayout bg = (ConstraintLayout)
                findViewById(R.id.constraintLayout1);

        Random rd = new Random();

        // Cach 1: Dung mảng Array
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.bg1);
        arrayList.add(R.drawable.bg2);
        arrayList.add(R.drawable.bg3);
        arrayList.add(R.drawable.bg4);

        int vitri = rd.nextInt(arrayList.size());
        current_color = arrayList.get(vitri);
        bg.setBackgroundResource(current_color);

        // Cach 2: Tạo màu ngau nhien
        while (true) {
            current_color = 2131034145 + rd.nextInt(100000);
            try {
                bg.setBackgroundResource(current_color);
                break;
            } catch (Exception e) {
                //  Block of code to handle errors
            }
        }
    }
}