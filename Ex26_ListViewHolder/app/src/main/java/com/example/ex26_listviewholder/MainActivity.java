package com.example.ex26_listviewholder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 2_ 대량의 데이터 만들기 위한 배열
    ArrayList<String> items = new ArrayList<>();
    ListView listView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 3_ 대량의 데이터 추가
        items.add( new String("aaa") );
        items.add( new String("bbb") );
        items.add( new String("ccc") );
        items.add( new String("ddd") );
        items.add( new String("eee") );
        items.add( new String("aaa") );
        items.add( new String("bbb") );
        items.add( new String("ccc") );
        items.add( new String("ddd") );
        items.add( new String("eee") );
        items.add( new String("aaa") );
        items.add( new String("bbb") );
        items.add( new String("ccc") );
        items.add( new String("ddd") );
        items.add( new String("eee") );
        items.add( new String("aaa") );
        items.add( new String("bbb") );
        items.add( new String("ccc") );
        items.add( new String("ddd") );
        items.add( new String("eee") );
        items.add( new String("aaa") );
        items.add( new String("bbb") );
        items.add( new String("ccc") );
        items.add( new String("ddd") );
        items.add( new String("eee") );

        // 11_ 아답터 객체 만들어서 리스트 뷰를 세팅해보자.
        listView = findViewById(R.id.listview);
        adapter = new MyAdapter(this,items);
        listView.setAdapter(adapter);
    }
}