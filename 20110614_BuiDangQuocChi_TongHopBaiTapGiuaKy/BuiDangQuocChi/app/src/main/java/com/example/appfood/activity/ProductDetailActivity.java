package com.example.appfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appfood.api.APIService;
import com.example.appfood.model.MessModel;
import com.example.appfood.model.ProductDetailModel;
import com.example.appfood.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView name, price, description;
    private ImageView image;
    private LinearLayout layoutHome, layoutProfile;
    private Button add;

    private ProductDetailModel productDetailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            String id = bundle.getString("id_product");
            Log.e("DAQUACHONAY", id);
            AnhXa();

            GetProductDetail(id);
            ToClick();
        }
    }

    private void ToClick() {
        layoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        name = findViewById(R.id.name_product);
        price = findViewById(R.id.price_product);
        description = findViewById(R.id.description_product);
        image = findViewById(R.id.img_product);
        add = findViewById(R.id.btnAdd_product);
        layoutHome = findViewById(R.id.layoutHome);
        layoutProfile = findViewById(R.id.layoutProfile);

    }

    private void GetProductDetail(String id) {
        APIService.apiService.getProductDetail(id)
                .enqueue(new Callback<MessModel>() {
                    @Override
                    public void onResponse(Call<MessModel> call, Response<MessModel> response) {
                        if (response.isSuccessful()) {
                            productDetailModel = response.body().getResult().get(0);
                            name.setText(productDetailModel.getMeal());
                            price.setText("$ " + productDetailModel.getPrice());
                            description.setText(productDetailModel.getInstructions());
                            Glide.with(ProductDetailActivity.this).load(productDetailModel.getStrmealthumb()).into(image);

                        } else {
                            Toast.makeText(ProductDetailActivity.this, "Khong co du lieu" + response.body(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<MessModel> call, Throwable t) {
                        Toast.makeText(ProductDetailActivity.this, "Lay API that bai", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}