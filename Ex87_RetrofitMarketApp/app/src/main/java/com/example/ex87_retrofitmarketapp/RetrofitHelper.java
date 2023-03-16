package com.example.ex87_retrofitmarketapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {

    public static Retrofit getRetrofitInstance(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://tjdrjs0803.dothome.co.kr");
        // 6_ 여러개의 컨버터를 사용할때는 순서가 중요하다! Scalars 먼저 해야 에러가 안난다!
        builder.addConverterFactory(ScalarsConverterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }
}
