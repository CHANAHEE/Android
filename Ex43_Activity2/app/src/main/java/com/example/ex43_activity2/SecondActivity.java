package com.example.ex43_activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 7_ 이 액티비티를 실행시켜 준 Intent(택배기사) 소환하자. 새로 만드는게 아님!!
        Intent intent = getIntent();

        // 8_ 택배기사 Intent 객체를 통해 전달된 Extra 데이터가 있으면!! 받기.
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age",0);
        // 9_ 기본형 자료형은 null 값을 가질수 없기 떄문에 defaultValue 파라미터가 필요하다!


        // 10_ name 은 제목줄에, age 는 텍스트뷰에!
        tv = findViewById(R.id.tv);
        getSupportActionBar().setTitle(name);
        tv.setText(age+"");

    }
}