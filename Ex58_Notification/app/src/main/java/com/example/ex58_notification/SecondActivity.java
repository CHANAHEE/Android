package com.example.ex58_notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 44_ 두번째 액티비티임을 알기 쉽게 제목줄을 바꿔보자.
        getSupportActionBar().setTitle("SECOND ACTIVITY");
    }
}