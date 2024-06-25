package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Backgroud_Switch extends AppCompatActivity {

    private int current_color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgroud_switch);

        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.constraintLayout1);

        // BÀI TẬP 2: THAY ĐỔI NÊN KHI CLICK SWITCH
        // BÀI TẬP 2: THAY ĐỔI NỀN APP KHI BẤM VÀO SWITCH

        Switch switch_bg = (Switch) findViewById(R.id.switch_bg);
        current_color = R.drawable.bg1;
        switch_bg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    bg.setBackgroundResource(R.drawable.ic_launcher_background);
                }
                else {
                    bg.setBackgroundResource(current_color);
                }
            }
        });
    }
}