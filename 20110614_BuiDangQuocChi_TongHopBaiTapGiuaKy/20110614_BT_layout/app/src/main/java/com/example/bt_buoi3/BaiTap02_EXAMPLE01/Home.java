package com.example.bt_buoi3.BaiTap02_EXAMPLE01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.bt_buoi3.R;

public class Home extends AppCompatActivity {

    private TextView perent_id;

    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        Bundle extrast = getIntent().getExtras();
        data = extrast.getString("Username");

        perent_id = (TextView) findViewById(R.id.perent_id);
        perent_id.setText(perent_id.getText().toString() + data);
    }
}