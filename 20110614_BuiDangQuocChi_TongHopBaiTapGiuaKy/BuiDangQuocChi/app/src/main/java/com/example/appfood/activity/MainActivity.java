package com.example.appfood.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfood.adapter.CategoryAdapter;
import com.example.appfood.adapter.ProductAdapter;
import com.example.appfood.api.APIService;
import com.example.appfood.model.CategoryModel;
import com.example.appfood.model.ProductModel;
import com.example.appfood.model.UserModel;
import com.example.appfood.retrofitClient.RetrofitClient;
import com.example.appfood.util.SharedPrefManager;
import com.example.appfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvUserName;
    ImageView imgUserImage;
    BottomNavigationView bottomNavigationView;

    RecyclerView recyclerViewCategory;
    RecyclerView recyclerViewProduct;

    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    LinearLayout layoutHome, layoutProfile;

    UserModel userModel;

    APIService apiService;

    List<CategoryModel> categoryModels;
    List<ProductModel> productModels;

    SharedPreferences sharedPreferences;

    // View Flipper
    ViewFlipper viewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AnhXa();
        GetInfo();
        GetCategory();
        GetProduct();
        ActionViewFliper();

        imgUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

        ToClick();
    }

    private void AnhXa(){
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        imgUserImage = (ImageView) findViewById(R.id.imgUserImage);
        recyclerViewCategory = (RecyclerView) findViewById(R.id.recyclerViewCategory);
        recyclerViewProduct =(RecyclerView) findViewById(R.id.recyclerViewProduct);
        layoutHome = findViewById(R.id.layoutHome);
        layoutProfile = findViewById(R.id.layoutProfile);
        viewFlipper = findViewById(R.id.viewFlipper);
    }

    private void ToClick() {

        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetInfo() {
        userModel = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        tvUserName.setText("Hi! " + userModel.getUsername());
        Glide.with(MainActivity.this)
                .load(userModel.getImages())
                .into(imgUserImage);
    }

    private void GetCategory() {
        //Gọi Interface trong API
        apiService = RetrofitClient.getRetrofitCategory().create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (response.isSuccessful()) {
                    categoryModels = response.body();

                    //Khởi tạo Adapter
                    categoryAdapter = new CategoryAdapter(categoryModels, MainActivity.this);
                    recyclerViewCategory.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    recyclerViewCategory.setLayoutManager(layoutManager);
                    recyclerViewCategory.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                }
                else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t)  {
                Log.d("logg", t.getMessage());
            }
        });
    }

    private void GetProduct() {
        apiService = RetrofitClient.getRetrofitProduct().create(APIService.class);
        apiService.getProductAll().enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if (response.isSuccessful()) {
                    productModels = response.body();

                    //Khởi tạo Adapter
                    categoryAdapter = new CategoryAdapter(categoryModels, MainActivity.this);
                    recyclerViewCategory.setHasFixedSize(true);

                    productAdapter = new ProductAdapter(productModels, MainActivity.this);
                    recyclerViewProduct.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
                    recyclerViewProduct.setLayoutManager(layoutManager);
                    recyclerViewProduct.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }

    private void ActionViewFliper(){
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://designs.princesharma.in/wp-content/uploads/2021/08/20201105_205823-scaled.jpg");
        mangquangcao.add("https://img.freepik.com/premium-vector/online-food-promotion-with-mobile-square-banner-template_553141-149.jpg");
        mangquangcao.add("https://tea-3.lozi.vn/v1/images/resized/banner-mobile-4747-1676348590?w=393&type=o");

        for (int i = 0; i < mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);

    }
}