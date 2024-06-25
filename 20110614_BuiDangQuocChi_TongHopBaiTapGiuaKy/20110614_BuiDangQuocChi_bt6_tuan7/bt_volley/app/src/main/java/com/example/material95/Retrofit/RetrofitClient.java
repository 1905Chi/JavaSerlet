package com.example.material95.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if(retrofit ==null){
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl("http://app.iotstar.vn/appfoods/");
            builder.addConverterFactory(GsonConverterFactory.create());
            retrofit = builder
                    .build();
        }
        return  retrofit;
    }
}
