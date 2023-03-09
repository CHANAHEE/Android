package com.example.ex80_activitylifecyclemethod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i("TAG","Second onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG","Second onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG","Second onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG","Second onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG","Second onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG","Second onDestroy");
    }
}