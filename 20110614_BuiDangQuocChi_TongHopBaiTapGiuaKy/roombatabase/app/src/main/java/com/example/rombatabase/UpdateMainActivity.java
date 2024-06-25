package com.example.rombatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMainActivity extends AppCompatActivity {
    private EditText editTextUserName;
    private EditText editTextAddress;
    private Button btnUpdate;
    private User users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_main);
        AnhXa();
        users= (User)getIntent().getExtras().get("object name");
        if(users!=null)
        {
            editTextAddress.setText(users.getUsername());
            editTextAddress.setText(users.getUsername());
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUsers();
            }
        });
    }

    private void updateUsers() {
        String username=  editTextUserName.getText().toString().trim();
        String address= editTextAddress.getText().toString().trim();
        if(TextUtils.isEmpty(username)|| TextUtils.isEmpty(address)){
            return;
        }
        users.setUsername(username);
        users.setAddress(address);
        UserDatabase.getInstance(this).userDao().update(users);
        Toast.makeText(this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK,intentResult);
        finish();

    }

    private void AnhXa() {
        editTextAddress=findViewById(R.id.tvaddr);
        editTextUserName=findViewById(R.id.tvName);
        btnUpdate=findViewById(R.id.button3);

    }
}