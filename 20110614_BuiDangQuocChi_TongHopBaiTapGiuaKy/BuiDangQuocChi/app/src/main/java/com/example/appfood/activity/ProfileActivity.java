package com.example.appfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appfood.model.UserModel;
import com.example.appfood.util.SharedPrefManager;
import com.example.appfood.R;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    TextView id, userName, userEmail, gender;
    Button btnLogout;
    ImageView imageViewprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            id = findViewById(R.id.txtViewId);
            userName = findViewById(R.id.txtViewUserName);
            userEmail = findViewById(R.id.txtViewEmail);
            gender = findViewById(R.id.txtViewGender);
            btnLogout = findViewById(R.id.btnLogout);

            imageViewprofile = findViewById(R.id.imageViewProfile);


            UserModel user = SharedPrefManager.getInstance(this).getUser();
            id.setText(String.valueOf(user.getId()));
            userEmail.setText(user.getEmail());
            gender.setText(user.getGender());
            userName.setText(user.getUsername());
            Glide.with(getApplicationContext()).load(user.getImages()).into(imageViewprofile);
            btnLogout.setOnClickListener(this);

            imageViewprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfileActivity.this, UploadActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClick(View view){
        if(view.equals(btnLogout)){
            //SharedPrefManager.getInstance(getApplicationContext()).logout();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}