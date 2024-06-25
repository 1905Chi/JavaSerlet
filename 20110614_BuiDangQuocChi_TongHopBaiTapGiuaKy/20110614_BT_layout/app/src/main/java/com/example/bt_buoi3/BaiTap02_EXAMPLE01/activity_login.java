package com.example.bt_buoi3.BaiTap02_EXAMPLE01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bt_buoi3.R;


import java.util.Objects;

public class activity_login extends AppCompatActivity {

    private String username, password;
    private EditText et_username, et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        ImageButton btnlogin = (ImageButton) findViewById(R.id.btnLogin);

        TextView tv_signup = (TextView) findViewById(R.id.tv_signup);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_put = new Intent(activity_login.this, activity_sigup.class);
                startActivity(intent_put);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = et_username.getText().toString();
                password = et_password.getText().toString();
                if (view == btnlogin) {
                    if (Objects.equals(username, "khang") && Objects.equals(password, "khang")) {
                        Intent i = new Intent(activity_login.this, Home.class);
                        int RE_CODE = 9;
                        i.putExtra("Username", username);
                        startActivityForResult(i, RE_CODE);
                    } else
                        Toast.makeText(activity_login.this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}