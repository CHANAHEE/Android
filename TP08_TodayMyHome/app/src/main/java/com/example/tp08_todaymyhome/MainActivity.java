package com.example.tp08_todaymyhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items;
    MyAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 채우기","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,0,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 채우기","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 채우기","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,0,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 ","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,0,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 채우기 미드 센추리 모던감성 32평 싱글라이프 하우스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓우스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,0,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 채우기 미드 센스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리모던감성 32평 싱글라이프 하우스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리 32평 싱글라이프 하우스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 추리 모던감성 32평 싱글라이프 하우스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 채우기","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 채우기","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 채우기","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 ","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 채우기 미드 센추리 모던감성 32평 싱글라이프 하우스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓우스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 넓은 공간감 채우기 미드 센스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리모던감성 32평 싱글라이프 하우스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리 32평 싱글라이프 하우스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));
        items.add(new Item("전문가 집들이","비우고 정리한 자리에 더 추리 모던감성 32평 싱글라이프 하우스","그안에디자인","스크랩 0","조회수 0",R.drawable.images,R.drawable.ic_action_bookmark,R.drawable.ic_action_name,R.drawable.images));

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,items);
        recyclerView.setAdapter(adapter);
    }
}