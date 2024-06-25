package com.example.rombatabase;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rombatabase.Adapter.UserAdapter;
import com.example.rombatabase.Adapter.iClickListnener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUserName;
    private EditText editTextAddress;
    private Button btnThem;
    private RecyclerView rc_list;
    private UserAdapter userAdapter;
    private List<User> userlist;
    private static final int MY_REQUEST_CODE=10;
    public  static final String TAG=MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        userAdapter= new UserAdapter(new iClickListnener() {
            @Override
            public void updateUser(User user) {

                clickUpdateUser(user);
            }
            @Override
            public void deleteUser(User user)
            {
                clickDeleteUser(user);
            }
        });
        userlist= new ArrayList<>();
        /// lấy danh sách trong room
        userlist=UserDatabase.getInstance(this).userDao().getAll();
        userAdapter.setData(userlist);
        rc_list.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rc_list.setLayoutManager(linearLayoutManager);
        rc_list.setAdapter(userAdapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
    }

    private void clickDeleteUser(User user) {
        new AlertDialog.Builder(this)
                .setTitle("confirm delete user")
                .setMessage("Are you sure ?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserDatabase.getInstance(MainActivity.this).userDao().delete(user);
                        Toast.makeText(MainActivity.this,"Đã xóa thành công",Toast.LENGTH_SHORT).show();
                       loadData();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    private void addUser()
    {
        String username=editTextUserName.getText().toString().trim();
        String address= editTextAddress.getText().toString().trim();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(address))
        {
            return;
        }
        User users= new User(username,address);
        /// add vào form
        UserDatabase.getInstance(this).userDao().insertAll(users);
        Toast.makeText(this,"thêm user thành công",Toast.LENGTH_SHORT).show();

        //// xóa dữ liệu trên edit text

        editTextAddress.setText("");
        editTextUserName.setText("");
        hideSoftKeyboard();

        /// lấy ds user trong room
        userlist=UserDatabase.getInstance(this).userDao().getAll();
        userAdapter.setData(userlist);

        User user = new User(username,address);
        if(isCheckExist(user))
        {
            Toast.makeText(this,"user đã tồn tại",Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void hideSoftKeyboard(){
        try{
            InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }catch (NullPointerException ex)
        {
            ex.printStackTrace();
        }
    }

    private void AnhXa() {
        editTextUserName=findViewById(R.id.tvName);
        editTextAddress=findViewById(R.id.tvaddr);
        btnThem=findViewById(R.id.button3);
        rc_list=findViewById(R.id.recys);

    }
    private boolean isCheckExist(@NonNull User user)
    {
        List<User>list = UserDatabase.getInstance((Context) this).userDao().checkUser(user.getUsername());
        return list!=null && !list.isEmpty();
    }
    private void clickUpdateUser(User user)
    {
        Intent intent = new Intent(MainActivity.this, UpdateMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object name",user);
        intent.putExtras(bundle);
        mActivityResultLauncher.launch(intent);
    }
    private ActivityResultLauncher<Intent> mActivityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG,"onActivityResult");
                    if(result.getResultCode()==Activity.RESULT_OK){
                        Intent data= result.getData();
                        if(data== null)
                            return;
                        loadData();
                    }

                }
            }
    );
    public  void loadData()
    {
        userlist=UserDatabase.getInstance(this).userDao().getAll();
        userAdapter.setData(userlist);
    }



}