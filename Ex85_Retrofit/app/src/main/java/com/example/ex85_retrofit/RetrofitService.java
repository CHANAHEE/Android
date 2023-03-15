package com.example.ex85_retrofit;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

// 5_ 인터페이스는 클래스와 비슷하지만 추상메소드만 갖고있다는 점에서 차이를 가진다.
// 그리고 이 인터페이스에는 원하는 작업을 위한 코드가 아니라!! 요구사항을 명세해야한다.
public interface RetrofitService {

    // 6_ 단순하게 GET 방식으로 json 파일을 읽어오기
    @GET("Retrofit/board.json") // 7_ 어노테이션을 활용하여 어떤 json 파일을 얻어올지 명시
    Call<Item> getBoardJson(); // 8_ 제네릭에는 어떤 결과로 받을지 명시. 우리는 Item 객체로 받을 예정~

    // 14_ 경로의 이름을 위 1번처럼 board.json 으로 정해놓지 않고 사용자에게 파라미터로 전달받아 지정할 수 있음. [@path]
    @GET("{aaa}/{bbb}")
    Call<Item> getBoardJsonByPath(@Path("aaa") String path,@Path("bbb") String file);

    @GET("Retrofit/getTest.php") // getTest.php 은 만들어줘야한다!! 안그럼 에러나지~
    Call<Item> getMethodTest(@Query("name") String name, @Query("msg") String message, @Query("age") int age);

    // 2번과 3번 섞어서 테스트해보자!



    // 24_ GET 방식으로 값을 보낼 때, Map Collection 을 이용하여 전달할수있음.
    @GET("Retrofit/getTest.php")
    Call<Item> getMethodTest2(@QueryMap Map<String,String> datas);

    // 31_ POST 방식으로 값 전달 [@Body]
    @POST("Retrofit/postTest.php")
    Call<Item> postMethodTest(@Body Item item ); // 32_ 객체의 멤버변수가 자동으로 식별자가 된다.


    // 41_ POST 방식으로 단일 데이터 전달. @Field
    // 42_ 단, @Field 를 사용하려면 반드시 @FormUrlEncoded 와 함께 사용해야한다.
    @FormUrlEncoded
    @POST("Retrofit/postTest2.php")
    Call<Item> postMethodTest2( @Field("name") String name, @Field("msg") String message, @Field("age") int age);

    // 44_ GET 방식으로 json Array 값 일겅와서 ArrayList<Item> 에 곧바로 저장.
    @GET("Retrofit/boardArr.json")
    Call<ArrayList<Item>> getJsonBoardArr();

    // 45_ GSON 을 통해 자동으로 Item 객체로 파싱하지 않고, 그냥 문자열로 응답결과 받아오기.
    @GET("Retrofit/board.json")
    Call<String> getJsonString();
}
