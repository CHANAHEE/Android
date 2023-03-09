package com.example.ex79_databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.ex79_databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // 5_ DataBinding 의 기능을 통해 액티비티에 보여줄 내용물(뷰) 설정
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        // 6_ 이제 xml 문서의 <data> 요소안에 있는 <variable> 의 type 에 지정한
        // 클래스를 객체로 만들어서 set() 해주면 그와 연결된 View 들의 값이 보여짐
        binding.setUser(new User("sam",20,true));


    }
}