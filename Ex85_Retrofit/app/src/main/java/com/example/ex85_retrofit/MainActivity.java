package com.example.ex85_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ex85_retrofit.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn1.setOnClickListener(view -> clickBtn1());
        binding.btn2.setOnClickListener(view -> clickBtn2());
        binding.btn3.setOnClickListener(view -> clickBtn3());
        binding.btn4.setOnClickListener(view -> clickBtn4());
        binding.btn5.setOnClickListener(view -> clickBtn5());
        binding.btn6.setOnClickListener(view -> clickBtn6());
        binding.btn7.setOnClickListener(view -> clickBtn7());
        binding.btn8.setOnClickListener(view -> clickBtn8());
    }

    void clickBtn1(){
        // 1_ 단순하게 GET 방식으로 서버에 있는 json 파일을 읽어오자.

        // 2_ 레트로핏의 사용을 하기 위한 5단계
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://tjdrjs0803.dothome.co.kr"); // 기본 주소까지는 설정되었음
        builder.addConverterFactory(GsonConverterFactory.create());
        // 3_ 컨버터를 만들어주는 공장을 만들자. 파라미터로는 어떤 공장을 만들지에 대한 정보를 줘야한다.
        // 우리는 json 파일을 읽어오기 위해 Gson 을 만들어야 하므로 Gson 컨버터 공장 객체를 넘기자.
        Retrofit retrofit = builder.build();

        // 4_ 원하는 GET,POST 등의 동작을 하는 인터페이스를 설계
        // 9_ RetrofitService.java 인터페이스를 설계 및 추상메소드 설계 : getBoardJson()

        // 10_ 인터페이스 객체를 생성하자. new 로 인터페이스 객체를 생성할수는 없기 떄문에
        // 레트로핏에게 요청하자.
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        // 11_ 4단계. 여기서 네트워크 작업을 하는게 아님!!!!
        Call<Item> call = retrofitService.getBoardJson();

        // 12_ 5단계. : 4단계에서 리턴된 Call 객체에게 네트워크 작업을 수행하도록 요청!!!
        // 단, 비동기 방식으로 처리함. Call 객체가 별도의 스레드를 만들어 사용한다~
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                // 13_ 파라미터로 전달된 응답객체로부터 GSON 라이브러리에 의해
                // 자동으로 Item 객체로 파싱되어 있는 데이터 값 얻어오기
                Item item = response.body();
                binding.tv.setText(item.name + " " + item.msg + " " + item.age);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                binding.tv.setText("failure : " + t.getMessage());

            }
        });

    }

    void clickBtn2(){
        // 15_ 경로의 이름을 고정하지 않고 파라미터로 전달하여 지정해보자.

        // 16_ Retrofit 객체 생성
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://tjdrjs0803.dothome.co.kr");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        // 17_ RetrofitService 인터페이스 설계 : getBoardJsonByPath()
        // 18_ Service 인터페이스 객체 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        // 19_ 추상 메소드 호출해서 네트워크 작업을 수행하는 Call 객체를 받자.
        Call<Item> call = retrofitService.getBoardJsonByPath("Retrofit","board.json");

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText(item.name +" " + item.msg + " " + item.age);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                binding.tv.setText("failure : " + t.getMessage());
            }
        });
    }

    void clickBtn3(){
        // 20_ GET 방식으로 데이터를 서버에 전달해보자.
        // 원래는 뭐 EditText 같은걸로 사용자의 데이터를 받아서 데이터를 준비해야하나,
        // 테스트 목적이므로, 데이터를 미리 준비해두자. 그리고 보통
        // 사용자가 데이터를 입력하므로, 데이터를 전달해서 서버로 전송하면 된다!
        String name = "홍길동";
        String message = "안녕하세요";
        int age = 20;

        // 21_ Retrofit 객체 생성 -> 따로 클래스를 만들어주었다~ 재활용이 가능하겠군.
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();

        // 22_ Service 인터페이스 객체 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        // 23_ 원하는 작업의 추상메소드 호출하자.


        Call<Item> call = retrofitService.getMethodTest(name,message,age);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText(item.name + " " + item.msg + " " + item.age);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                binding.tv.setText("Failure : " + t.getMessage());
            }
        });

    }

    void clickBtn4(){
        // 25_ GET 방식으로 전달할 데이터들을 Map Collection 으로 한방에 보내기. 3번처럼 따로 식별자를 명시하지 않아도 됨!

        // 26_보낼 데이터들을 Map 객체로 만들자.
        HashMap<String,String> datas = new HashMap<>();
        datas.put("name","robin");
        datas.put("msg","nice to meet you");
        datas.put("age","20");

        // 27_ Retrofit 객체 생성
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        // 28_ Service 인터페이스와 추상메소드 설계 - getMethodTest2()
        // 이미 설계되어있음!
        // 29_ Service 인터페이스 객체 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        // 30_ 원하는 작업 추상메소드 호출
        Call<Item> call = retrofitService.getMethodTest2(datas);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText(item.name + " , "+item.msg);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

    void clickBtn5(){
        // 33_ POST 방식으로 전달할 객체를 만들자
        // 34_ 레트로핏이 자동으로 객체를 json 문자열로 변환하여 전송한다.
        Item item = new Item("kim","Good Afternoon",20);

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<Item> call = retrofitService.postMethodTest(item);

        // 35_ 다 만들었으면 php 파일 만들러가자~
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item i = response.body();
                binding.tv.setText(i.name + " " + i.msg + " " + i.age);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });

    }

    void clickBtn6(){
        // 43_ POST 방식으로 개별 데이터 전송
        String name = "ROSA";
        String message = "Have a good day";
        int age = 25;

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<Item> call = retrofitService.postMethodTest2(name,message,age);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText(item.name + ", " + item.msg + ", " + item.age);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

    void clickBtn7(){
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<Item>> call = retrofitService.getJsonBoardArr();
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                ArrayList<Item> items = response.body();
                binding.tv.setText(items.size() + "");
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {

            }
        });
    }

    void clickBtn8(){
        // 46_ 서버의 응답결과가 json 이 아닐때 사용..
        // 47_ 서버의 응답결과를 그냥 String 으로 받아보기 [No Parse]
        // 48_ 결과를 String 으로 받으려면 ScalarsConverter 필요 - 추가 라이브러리임.
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://tjdrjs0803.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit = builder.build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<String> call = retrofitService.getJsonString();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                binding.tv.setText(s);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}