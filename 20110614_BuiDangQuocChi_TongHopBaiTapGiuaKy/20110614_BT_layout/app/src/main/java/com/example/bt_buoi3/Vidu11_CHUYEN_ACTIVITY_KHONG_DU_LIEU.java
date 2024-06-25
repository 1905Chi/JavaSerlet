package com.example.bt_buoi3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Vidu11_CHUYEN_ACTIVITY_KHONG_DU_LIEU extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidu11_chuyen_khong_du_lieu);

        Button btn_change = findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_put = new Intent(Vidu11_CHUYEN_ACTIVITY_KHONG_DU_LIEU.this, Vidu10_Implicit_Intent_Call.class);
                startActivity(intent_put);
            }
        });

        Intent intent = getIntent();
        // Nhận dữ liệu từ Ví dụ 12
        // Cách 1:
        int intValue = intent.getIntExtra("int_key", 0);
        char charValue = intent.getCharExtra("char_key", 'a');
        float floatValue = intent.getFloatExtra("float_key", 0f);
        boolean booleanValue = intent.getBooleanExtra("boolean_key", false);
        String stringValue = intent.getStringExtra("string_key");
        int[] intArrayValue = intent.getIntArrayExtra("int_array_key");
        char[] charArrayValue = intent.getCharArrayExtra("char_array_key");
        Rect rect = intent.getParcelableExtra("rect_key");

        // Cách 2: Qua Bundle
        /*
        Bundle bundle = intent.getExtras();
        char charValue = bundle.getChar("char");
        int intValue = bundle.getInt("int");
        String stringValue = bundle.getString("string");
        float floatValue = bundle.getFloat("float");
        double doubleValue = bundle.getDouble("double");
        long longValue = bundle.getLong("long");
        Rect rect = bundle.getParcelable("parcelable");
        */
        TextView tv_testvd12 = findViewById(R.id.tv_testvd12);
        tv_testvd12.setText(stringValue);

    }
}