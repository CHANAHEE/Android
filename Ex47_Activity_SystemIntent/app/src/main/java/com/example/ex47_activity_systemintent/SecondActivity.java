package com.example.ex47_activity_systemintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        Intent intent2 = new Intent();
        findViewById(R.id.btn).setOnClickListener(view -> {
            intent2.putExtra("name","NAME");
            intent2.setAction("android.intent.action.MAIN");

            startActivity(intent2);
            finish();
        });


    }
}