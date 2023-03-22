package com.example.ex93_kotlinopenapinaversearch


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {


//    @GET("v1/search/shop.json")
//    fun searchDataByString(@Query("query") query:String, @Query("display") display:Int):Call<String>

    @GET("v1/search/shop.json?display=100")
    fun searchDataByString(@Header("X-Naver-Client-Id") clientId:String,@Header("X-Naver-Client-Secret") clientSecret:String,@Query("query") query:String,):Call<String>


    // 8_ 헤더값이 고정적이라면, 매번 파라미터로 받지말고 @Headers 로 처리하자
    @Headers("X-Naver-Client-Id: bED_Bn4IzCayX3KnlLXq","X-Naver-Client-Secret: nc1pKiIc6F")
    @GET("v1/search/shop.json?display=100")
    fun searchData(@Query("query") query: String):Call<NaverSearchApiResponse>
    // 9_ @Query 를 사용하면 GET 방식이므로, @GET 뒤에 있는 URL 뒤에 @Query () 괄호안에있는게 자동으로 뒤에 ? 랑 같이 붙어버림
    // 13_ Call 이 가져올 데이터 형식은 Naver... 이다!
}