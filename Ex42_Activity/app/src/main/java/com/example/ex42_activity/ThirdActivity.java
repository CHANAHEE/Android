package com.example.ex42_activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // 13_ 각 액션바의 타이틀을 지정해보자
        getSupportActionBar().setTitle("세번째");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // 14_ [업버튼] 이 클릭되었을 때 자동으로 발동하는 콜백 메소드

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }
}
