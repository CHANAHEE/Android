package com.example.ex24_gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayAdapter adapter;

    ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2_ 대량의 데이터 추가
        arrayList.add( new String("aaa"));
        arrayList.add("bbb");
        arrayList.add("ccc");
        arrayList.add("ddd");
        arrayList.add("eee");
        arrayList.add("fff");

        // 3_ Id 값 찾아오기
        gridView = findViewById(R.id.gridview);
        // 4_ 안드로이드 자체의 R 장부도 있다! 참고해보자~
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arrayList.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }
}