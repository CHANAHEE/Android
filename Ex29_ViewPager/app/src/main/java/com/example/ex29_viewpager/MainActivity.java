package com.example.ex29_viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items = new ArrayList<>();
    ViewPager2 viewPager2;
    MyAdapter adapter;
    Button btnPrev, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 4_ 대량의 데이터를 추가. 실무에서는 DB 나 서버에서 데이터를 읽어온다.
        items.add(new Item("이미지 1번",R.drawable.bg_one01));
        items.add(new Item("이미지 2번",R.drawable.bg_one02));
        items.add(new Item("이미지 3번",R.drawable.bg_one03));
        items.add(new Item("이미지 4번",R.drawable.bg_one04));
        items.add(new Item("이미지 5번",R.drawable.bg_one05));
        items.add(new Item("이미지 6번",R.drawable.bg_one06));
        items.add(new Item("이미지 7번",R.drawable.bg_one07));
        items.add(new Item("이미지 8번",R.drawable.bg_one08));
        items.add(new Item("이미지 9번",R.drawable.bg_one09));
        items.add(new Item("이미지 10번",R.drawable.bg_one10));


        // 8_ 어댑터 만들고 붙이기
        viewPager2 = findViewById(R.id.pager);
        adapter = new MyAdapter(this,items);
        viewPager2.setAdapter(adapter);


        // 9_ 버튼 클릭 이벤트
        btnNext = findViewById(R.id.next);
        btnPrev = findViewById(R.id.prev);


        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 10_ 뷰페이져의 현재 위치(인덱스번호)를 먼저 얻어와서 전 페이지로 넘겨주는 역할
                int position = viewPager2.getCurrentItem();
                // 11_ 현재 위치를 이전번호로 지정
                viewPager2.setCurrentItem(--position,true);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewPager2.getCurrentItem();
                viewPager2.setCurrentItem(++position,false);
            }
        });
    }
}