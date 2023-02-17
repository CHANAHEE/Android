package com.example.ex27_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 5_ 대량의 데이터
    ArrayList<Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 6_ 대량의 데이터를 추가하자

        items.add(new Item("sam","hello"));
        items.add(new Item("robin","me"));
        items.add(new Item("kim","love"));
        items.add(new Item("hong","why"));
        items.add(new Item("cho","peace"));
        items.add(new Item("lee","like"));
        items.add(new Item("Mee","hug"));

        // 21_ 리사이클러뷰(어댑터뷰)에 어댑터 붙이자.
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,items);
        recyclerView.setAdapter(adapter);

        // 22_ 리사이클러뷰의 아이템뷰 1개를 클릭했을 때 반응하기 - 반응하는 리스너 처리가 없다...
        // 23_ 그래서 리스너 처리를 하려면 아이템 뷰 한개를 만드는 MyAdapter 에서 처리해야한다.

    }
}