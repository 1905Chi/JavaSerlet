package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Switch_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        //switch
        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { //isChecked = true
                    Toast.makeText(Switch_Activity.this, "Wifi đang bật", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Switch_Activity.this, "Wifi đang tắt", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}