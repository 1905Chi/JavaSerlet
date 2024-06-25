package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.RadioGroup;

public class Radio_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio2);

        //RadioGroup
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.bg_Radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //checkID trả về ID radio
                switch (checkedId){
                    case R.id.radioButton:
                        bg.setBackgroundResource(R.drawable.bg3);
                        break;
                    case R.id.radioButton2:
                        bg.setBackgroundResource(R.drawable.bg4);
                        break;
                }
            }
        });
    }
}