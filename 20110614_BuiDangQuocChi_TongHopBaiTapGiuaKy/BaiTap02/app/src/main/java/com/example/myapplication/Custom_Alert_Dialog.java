package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;

public class Custom_Alert_Dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_alert_dialog);

        DiaLog1();
    }

    //tạo hàm Dialog
    private void DiaLog1(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(false);
        //ánh xạ
        EditText etMSSV = (EditText)
                dialog.findViewById(R.id.et_Mssv);
        EditText etHoTen = (EditText)
                dialog.findViewById(R.id.et_HoTen);
        //viết code sự kiện
        //bắt sự kiện Dialog
        dialog.show(); //hủy gọi dialog.dismiss();
    }

}