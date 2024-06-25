package com.example.baitap2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private CheckBox checkBoxRemeberMe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.login || actionId == EditorInfo.IME_NULL) {
                    attempLogin();
                    return true;
                }
                return false;
            }
        });
        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempLogin();
            }
        });
        checkBoxRemeberMe = findViewById(R.id.checkBoxRememberMe);
        if(!new PreManager(this).isUserLogedOut()){
            startHomeActivity();
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish();
    }



    private void attempLogin() {
        //reset errors
        mEmailView.setError(null);
        mPasswordView.setError(null);
        // Lưu trữ giá trị biến đăng nhập
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        //check for a vaild password
        if(!TextUtils.isEmpty(password) && !isPasswordVaild(password)){
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        //check for a vaild email
        if(TextUtils.isEmpty(email)){
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        else if(!isVaildEmail(email)){
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if(cancel){
            focusView.requestFocus();
        }
        else {if(checkBoxRemeberMe.isChecked()){
            saveLoginDetail(email, password);
        }
            startHomeActivity();
        }

    }

    private void saveLoginDetail(String email, String password) {
        new PreManager(this).saveLoginDetails(email,password);
    }

    private boolean isVaildEmail(String email) {
        return email.contains("@");

    }

    private boolean isPasswordVaild(String password) {
        return  password.length() > 4;
    }
}