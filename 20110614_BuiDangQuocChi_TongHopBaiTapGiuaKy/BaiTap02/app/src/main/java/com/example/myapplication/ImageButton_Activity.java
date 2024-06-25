package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ImageButton_Activity extends AppCompatActivity {

    private ImageView mg1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_button);

        mg1 = (ImageView) findViewById(R.id.img_button);
        ImageButton img2 = (ImageButton)
                findViewById(R.id.imageButton1);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mg1.setImageResource(R.drawable.koltin);
                mg1.getLayoutParams().width=550;
                mg1.getLayoutParams().height=550;
            }
        });

    }
}