package com.example.bttuan06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;


import com.example.bttuan06.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import vn.iostar.adapter.ListUserAdapter;
import vn.iostar.model.User;

public class MainActivity extends AppCompatActivity implements ListUserAdapter.OnItemClickListener{

    public ObservableField<String> title = new ObservableField<>();
    private ListUserAdapter listUserAdapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        title.set("Vi du ve DataBinding cho RecycleView");
        binding.setHome(this);
        setData();
        binding.rcView.setLayoutManager(new LinearLayoutManager(this));
        binding.rcView.setAdapter(listUserAdapter);

        listUserAdapter.setOnItemClickListener(this);
    }

    private void setData() {
        List<User> userList = new ArrayList<>();
        for (int i=0;i<20;i++){
            User user = new User();
            user.setFirstName("Quốc" +i);
            user.setLastName("Chí" +i);
            userList.add(user);
        }
        listUserAdapter = new ListUserAdapter(userList);
    }

    @Override
    public void itemClick(User user) {
        Toast.makeText(this, "bạn vừa click", Toast.LENGTH_SHORT).show();
    }
}