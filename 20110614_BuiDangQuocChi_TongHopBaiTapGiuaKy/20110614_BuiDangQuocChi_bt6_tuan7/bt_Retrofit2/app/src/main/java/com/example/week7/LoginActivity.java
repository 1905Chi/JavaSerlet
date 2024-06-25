package com.example.week7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    ImageView btnLogin;
    EditText edtPass, edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.Loginbtn);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SourceActivity.class);
                if(edtEmail.getText().toString().equals("quocchi@gmail.com") && edtPass.getText().toString().equals("huy") ){
                    Bundle bundle = new Bundle();
                    bundle.putString("username", "Bui Dang Quoc Chi");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    ShowMessage("Đăng Nhập Thành Công");
                }
                else {
                    ShowMessage("Đăng Nhập Thất Bại");
                }
            }
        });

    }

    public void ShowMessage(String text){
        Toast.makeText(this, text, Toast.LENGTH_LONG);
    }


}