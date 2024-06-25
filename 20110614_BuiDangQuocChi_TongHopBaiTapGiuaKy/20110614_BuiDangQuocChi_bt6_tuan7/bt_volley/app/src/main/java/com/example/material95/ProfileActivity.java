package com.example.material95;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.material95.Model.User;
import com.bumptech.glide.Glide;


public class ProfileActivity extends AppCompatActivity {
    TextView id,userName, userEmail, gender;
    Button btnLogout;
    ImageView imageViewpprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if(SharedPrefManager. getInstance (this) .isLoggedIn()){
           // id = findViewById (R. id.textViewId) ;
            userName = findViewById (R . id. textView4) ;
            userEmail =findViewById (R. id. textView5) ;
           // gender= findViewById (R . id.textViewGender) ;
            //btnLogout = findViewById (R .id.buttonLogout) ;
            User user = SharedPrefManager. getInstance (this) . getUser ();
            id.setText(String.valueOf(user.getId ()));
            gender.setText (user. getGender ());
            userName. setText (user. getName ());
            Glide.with(getApplicationContext()).load(user.getImages()).into(imageViewpprofile);
            btnLogout.setOnClickListener((View.OnClickListener) this);
        }
        else{
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        }
    }
    public void onClick (View view){
        if(view.equals(btnLogout)){
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
    }
}
