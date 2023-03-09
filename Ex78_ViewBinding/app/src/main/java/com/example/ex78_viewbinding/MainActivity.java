package com.example.ex78_viewbinding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ex78_viewbinding.databinding.ActivityMainBinding;
import com.example.ex78_viewbinding.databinding.ActivitySecondBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 1_ ViewBinding 은 라이브러리가 아니고 안드로이드 아키텍처 구성요소임.
    // 그래서 그냥 기능만 ON 하면됨. - build.gradle

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main); 4_ 이녀석은 지워줘야 한다.
        // 5_ Binding 객체가 만든 뷰를 액티비티가 보여줘야 하기때문.

        // 3_ Binding 객체 생성 - setContentView 대신 레이아웃 인플레이트를 통해 activity_main.xml 을 객체로 생성하여  화면에 띄워준다.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 6_ TextView 글씨 변경해보자! - 이미 binding 객체가 TextView 를 참조하고있음!
        binding.tv.setText("Nice to meet you");

        // 7_ 버튼 클릭 이벤트를 해보자!

        binding.btn.setOnClickListener(view -> {
            binding.tv.setText("what?");
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

        // 8_ 롱 클릭 이벤트도 해보자! 
        binding.btn.setOnLongClickListener(view -> {
            Toast.makeText(this, "long~ click", Toast.LENGTH_SHORT).show();
            return true;
        });

        // 9_ EditText 에 글씨 입력받아서 TextView 에 띄우기
        binding.btn2.setOnClickListener(view -> {
            String result = binding.et.getText().toString();
            binding.tvResult.setText(result);
        });

        // 10_ 화면의 일부분을 별도로 설계하여 관리하는 Fragment 에서 ViewBinding 을 사용해보자
        // 일단 fragment_my.xml 부터 설계하고, MyFragment 를 만들자!

        // 14_ 리사이클러뷰에서 뷰바인딩 해보자!
        // 15_ 일단 아이템 클래스 먼저 만들자!

    }

    // 18_ 대량의 데이터 준비하기!
    ArrayList<ItemVO> items = new ArrayList<>();
    MyAdapter2 adapter;
    @Override
    protected void onResume() {
        super.onResume();
        // 19_ 임의의 데이터 추가
        items.add(new ItemVO("ONE PIECE1",R.drawable.bg_one05));
        items.add(new ItemVO("ONE PIECE2",R.drawable.bg_one06));
        items.add(new ItemVO("ONE PIECE3",R.drawable.bg_one07));
        items.add(new ItemVO("ONE PIECE4",R.drawable.bg_one08));
        items.add(new ItemVO("ONE PIECE5",R.drawable.bg_one09));
        items.add(new ItemVO("ONE PIECE6",R.drawable.bg_one10));

        adapter = new MyAdapter2(this,items);
        binding.recycler.setAdapter(adapter);
    }
}