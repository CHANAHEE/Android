package com.example.ex84_gson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ex84_gson.databinding.ActivityMainBinding;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(view -> clickBtn());
        binding.btn2.setOnClickListener(view -> clickBtn2());
        binding.btn3.setOnClickListener(view -> clickBtn3());
    }
    void clickBtn3(){
        // 4_ JSONArray --> Person Object Array
        String jsonStr = "[{'name':'sam','age':20},{'name':'robin','age':25}]";
        Gson gson = new Gson();
        Person[] people = gson.fromJson(jsonStr,Person[].class);
        binding.tv.setText("객체 수 : "+people.length);
    }
    void clickBtn2(){
        // 3. Person 객체를 JSON 으로 된 문자열로 바꾸자.
        Person person = new Person("robin",25);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(person);
        binding.tv.setText(jsonStr);
    }
    void clickBtn(){
        // 1_ GSON library 를 이용하여 편하게 json 문자열을 분석하여 객체로 생성하자.
        // json 문자열 직접 만들자~ 시간없다~
        String jsonStr = "{'name':'sam','age':20}";

        // 2_ name, age 를 멤버로 가지는 Person 클래스 객체로 한방에 분석하여 변환하자 - GSON 을 이용.
        Gson gson = new Gson();
        Person person = gson.fromJson(jsonStr, Person.class);
        binding.tv.setText(person.name + " : " + person.age);

    }
}