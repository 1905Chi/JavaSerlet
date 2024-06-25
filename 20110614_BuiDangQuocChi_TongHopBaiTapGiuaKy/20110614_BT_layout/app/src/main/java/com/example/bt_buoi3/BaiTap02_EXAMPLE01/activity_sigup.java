package com.example.bt_buoi3.BaiTap02_EXAMPLE01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bt_buoi3.R;

public class activity_sigup extends AppCompatActivity {

    private EditText et_name, et_email, et_password;
    private String name, email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);

        ImageButton btn_sign_up = (ImageButton) findViewById(R.id.btn_SignUp);


        et_name = (EditText) findViewById(R.id.et_name_signup);
        et_email = (EditText) findViewById(R.id.et_email_signup);
        et_password = (EditText) findViewById(R.id.et_password_signup);
//        tv_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent_put = new Intent(activity_login.this, activity_sigup.class);
//                startActivity(intent_put);
//            }
//        });
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_name.getText().toString();
                email = et_email.getText().toString();
                pass = et_password.getText().toString();
                if (!email.isEmpty() && !name.isEmpty() && !pass.isEmpty()) {
                    Intent i = new Intent(activity_sigup.this, Home.class);
                    int RE_CODE = 9;
                    i.putExtra("Username", email);
                    startActivityForResult(i, RE_CODE);
                }
                else
                    Toast.makeText(activity_sigup.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}