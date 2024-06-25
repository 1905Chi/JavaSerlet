package com.example.bt_buoi3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Vidu13_CHUYEN_ACTIVITY_DOI_TUONG extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidu13_chuyen_doi_tuong);
        Button btn_change = findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Vidu13_CHUYEN_ACTIVITY_DOI_TUONG.this, Vidu12_CHUYEN_ACTIVITY_CO_DU_LIEU.class);
                Bundle bundle = new Bundle();
                Person p = new Person(1, "Phan Văn Đức Anh");
                bundle.putSerializable("person1", p);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}