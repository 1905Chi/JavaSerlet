package com.example.baitap2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Khai báo biến toàn cục
    Button buttonTxt;
    EditText userNameTxt, passwordTxt;
    CheckBox cbRememberMe;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        //khởi tạo sharepreferences
        sharedPreferences = getSharedPreferences("datalogin",MODE_PRIVATE);
        //lấy giá trị
        userNameTxt.setText(sharedPreferences.getString("taikhoan", ""));
        passwordTxt.setText(sharedPreferences.getString("matkhau", ""));
        cbRememberMe.setChecked(sharedPreferences.getBoolean("trangthai", false));
    }

    @SuppressLint("WrongViewCast")
    private void AnhXa() {
        buttonTxt = findViewById(R.id.buttonTxt);
        userNameTxt = findViewById(R.id.usernameTxt);
        passwordTxt = findViewById(R.id.passwordtxt);
        cbRememberMe = findViewById(R.id.cbmemberme);

        buttonTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameTxt.getText().toString().trim();
                String password = passwordTxt.getText().toString().trim();
                if (username.equals("admin") && password.equals("admin")){
                    Toast.makeText(MainActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                    //Nếu có check rememberme
                    if(cbRememberMe.isChecked()){
                        //Chỉnh sửa file lưu trữ
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", username);
                        editor.putString("matkhau", password);
                        editor.putBoolean("trangthai", true);
                        editor.commit(); // xác nhận việc lưu
                    }
                    else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("trangthai");
                        editor.commit();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}