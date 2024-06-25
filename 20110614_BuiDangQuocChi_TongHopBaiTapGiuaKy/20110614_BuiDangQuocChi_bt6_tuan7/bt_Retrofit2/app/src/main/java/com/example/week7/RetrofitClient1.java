package com.example.week7;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClient1 extends BaseClient{
    private static final String BASE_URL = "http://app.iotstar.vn/appfoods/";
    private static APIService apiService;
    public  static  APIService getInstance(){
        if(apiService ==null){
            return createService(APIService.class,BASE_URL);
        }
        return  apiService;
    }
//    private  void GetCategory(){
//
//        apiService =RetrofitClient1.getInstance();
//        apiService.getCategoriesAll().enqueue(new Callback<List<Category>>() {
//            @Override
//            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
//                if (response.isSuccessful()){
//                    categoryList = response.body();
//                    categoryAdapter = new Cate
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Category>> call, Throwable t) {
//
//            }
//        });
//    }
}
