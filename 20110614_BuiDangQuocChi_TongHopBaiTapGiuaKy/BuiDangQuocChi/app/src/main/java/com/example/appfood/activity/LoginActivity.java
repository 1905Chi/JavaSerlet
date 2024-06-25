package com.example.appfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfood.api.APIService;
import com.example.appfood.model.MessageModel;
import com.example.appfood.model.UserModel;
import com.example.appfood.retrofitClient.RetrofitClient;
import com.example.appfood.util.SharedPrefManager;
import com.example.appfood.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassword;

    private ImageView Login;

    UserModel userModel = new UserModel();

    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogin();
            }
        });

    }

    private void AnhXa(){
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Login = (ImageView) findViewById(R.id.btn_login);
    }

    private void UserLogin(){
        final String username = etUserName.getText().toString();
        final String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            etUserName.setError("Please enter your username");
            etUserName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }

        apiService = RetrofitClient.getRetrofitUserLogin().create(APIService.class);
        apiService.login(username, password).enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.isSuccessful()) {
                    userModel = response.body().getUser();
                    //Log.d("logg", response.body());
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(userModel);
                    finish();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }

    public void btnRegister_Onclick(View view) {
        UserLogin();
    }
}