package com.example.appfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.adapter.CategoryAdapter;
import com.example.appfood.adapter.ProductByCategoryIdAdapter;
import com.example.appfood.api.APIService;
import com.example.appfood.model.CategoryModel;
import com.example.appfood.model.ProductModel;
import com.example.appfood.retrofitClient.RetrofitClient;
import com.example.appfood.R;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductByCategoryIdActivity extends AppCompatActivity {

    RecyclerView recyclerViewProductByCategoryId;

    List<ProductModel> productModels;
    APIService apiService;

    CategoryModel categoryModel;

    ProductByCategoryIdAdapter productByCategoryIdAdapter;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_category_id);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            Intent intent = new Intent(ProductByCategoryIdActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            categoryModel = (CategoryModel) bundle.get("object_category");
            AnhXa();
            getProductByCategoryId(categoryModel.getId());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (categoryAdapter != null) {
            categoryAdapter.release();
        }
    }

    private void AnhXa() {
        recyclerViewProductByCategoryId = (RecyclerView) findViewById(R.id.recyclerViewProductByCategoryId);
    }

    private void getProductByCategoryId(String idcategory) {
        apiService = RetrofitClient.getRetrofitProduct().create(APIService.class);
        apiService.getProductByCategoryId(idcategory).enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if (response.isSuccessful()) {
                    productModels = response.body();
                    productByCategoryIdAdapter = new ProductByCategoryIdAdapter(productModels, ProductByCategoryIdActivity.this);
                    recyclerViewProductByCategoryId.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    recyclerViewProductByCategoryId.setLayoutManager(layoutManager);
                    recyclerViewProductByCategoryId.setAdapter(productByCategoryIdAdapter);
                    productByCategoryIdAdapter.notifyDataSetChanged();
                }
                else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });

    }
}