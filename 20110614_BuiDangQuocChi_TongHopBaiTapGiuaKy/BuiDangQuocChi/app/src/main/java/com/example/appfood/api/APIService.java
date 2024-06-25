package com.example.appfood.api;

import com.example.appfood.model.CategoryModel;
import com.example.appfood.model.ImageUpload;
import com.example.appfood.model.MessModel;
import com.example.appfood.model.MessageModel;
import com.example.appfood.model.ProductModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl("http://app.iotstar.vn/appfoods/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);
    @GET("categories.php")
    Call<List<CategoryModel>> getCategoryAll();

    @GET("lastproduct.php")
    Call<List<ProductModel>> getProductAll();

    @POST("registrationapi.php?apicall=login")
    @FormUrlEncoded
    Call<MessageModel> login(@Field("username") String username,
                             @Field("password") String password);

    @POST("getcategory.php")
    @FormUrlEncoded
    Call<List<ProductModel>> getProductByCategoryId(@Field("idcategory") String idcategory);

    @Multipart
    @POST("updateimages.php")
    Call<List<ImageUpload>> upload(@Part("id") RequestBody id, @Part MultipartBody.Part images);

    @FormUrlEncoded
    @POST("newmealdetail.php")
    Call<MessModel> getProductDetail(@Field("id") String id);
}
