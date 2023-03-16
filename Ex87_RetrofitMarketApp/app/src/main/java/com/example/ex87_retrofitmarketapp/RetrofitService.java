package com.example.ex87_retrofitmarketapp;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface RetrofitService {

    // 7_ POST 방식으로 보내기
    // 8_ 이미지 파일용 Part 와 나머지 문자열 데이터들 Part 로 구분하여 전송
    // 9_ 즉, 택배박스 2개로..
    @Multipart
    @POST("RetrofitMarket/insertDB.php")
    Call<String> postDataToServer(@PartMap Map<String,String> dataPart, @Part MultipartBody.Part filePart);

    // 16_ 서버에서 echo 한 json array 를 읽어와서 자동으로 ArrayList<MarketItem> 으로 파싱하는 작업
    @GET("RetrofitMarket/loadDB.php")
    Call<ArrayList<MarketItem>> getDataFromServer();

    // 서버 DB 에서 특정 Item 을 삭제하도록 요청하는 명세를 작성해보자.
    @GET("RetrofitMarket/deleteItem.php")
    Call<String> deleteItem(@Query("no") String no);
}
