package com.example.material95;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SourceActivity extends AppCompatActivity {

    TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.pro_trung);

        Bitmap cricle = ImageConverter.getRoundedCornerBitmap(bitmap,100);

        ImageView criculeImage = (ImageView) findViewById(R.id.imageView7);

        criculeImage.setImageBitmap(cricle);

        tvUserName = findViewById(R.id.tvUsername);

    }
}