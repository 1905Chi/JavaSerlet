package com.example.bt_buoi3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Vidu12_CHUYEN_ACTIVITY_CO_DU_LIEU extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidu12_chuyen_co_du_lieu);
        Button btn_change = findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Vidu12_CHUYEN_ACTIVITY_CO_DU_LIEU.this, Vidu11_CHUYEN_ACTIVITY_KHONG_DU_LIEU.class);

                // Cách 1: Trực tiếp
                intent.putExtra("int_key", 4);
                intent.putExtra("char_key", 'r');
                intent.putExtra("boolean_key", true);
                intent.putExtra("long_key", 323L);
                intent.putExtra("float_key", 3.2f);
                intent.putExtra("string_key", "Chuyen Activity trong Android");
                intent.putExtra("double", 32D);
                intent.putExtra("int_array_key", new int[]{1, 2, 9});
                intent.putExtra("boolean_array_key", new boolean[]{true, false, true, true});
                intent.putExtra("char_array_Key", new char[] {'e', 'i', 't', 'g', 'u', 'i', 'd', 'e'});
                intent.putExtra("rect_key", new Rect(0,0, 200, 200));


                // Cách 2: Qua Bundle
                /*
                Bundle bundle = new Bundle();
                bundle.putChar("char", 'e');
                bundle.putInt("int", 3);
                bundle.putString("string", "Phan Văn Đức Anh");
                bundle.putFloat("float", 5.2f);
                bundle.putDouble("double", 843D);
                bundle.putLong("long", 55343L);
                bundle.putParcelable("parcelable", new Rect(0, 0, 300, 300));
                intent.putExtras(bundle);


                */

                startActivity(intent);
            }
        });
        // Nhận person từ ví dụ 13
        Bundle bundle = getIntent().getExtras();
        Person person1 = (Person) bundle.getSerializable("person1");

        String test = person1.to_String();

        TextView tv_testvd13 = (TextView) findViewById(R.id.tv_testvd13);
        tv_testvd13.setText(test);
    }
}