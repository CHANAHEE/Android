package com.example.ex42_activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    // 2_ Activity 가 보여줄 뷰를 설정하기 위해 자동으로 실행되는 콜백 메서드

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // 3_ setContentView() 를 이용하여 화면을 띄우자! 근데 파라미터로 View 를 전달해야한다, 근데 우리는
        // 뷰를 하나만 전달할건 아니니 레이아웃 파일로 만들어서 전달하자!
    }
}
