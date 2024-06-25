package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Popup_Menu_Acyivity extends AppCompatActivity {

    private Button btnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu_acyivity);

        btnButton = (Button) findViewById(R.id.btn_Popup);
    }


    //popup menu
    private void ShowPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(Popup_Menu_Acyivity.this,btnButton);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting,popupMenu.getMenu());
//bắt sự kiện
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuSetting:
//lệnh
                        Toast.makeText(Popup_Menu_Acyivity.this,"Bạn đang chọn Setting",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menuShare:
                        btnButton.setText("Chia sẻ");
                        break;
                    case R.id.menuLogout:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }


}