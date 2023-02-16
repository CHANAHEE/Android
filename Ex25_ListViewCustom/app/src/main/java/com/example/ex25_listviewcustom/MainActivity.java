package com.example.ex25_listviewcustom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items = new ArrayList<>();

    ListView listView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 4_ 대량의 데이터 추가하기
        items.add(new Item("전현무","대한민국",R.drawable.flag_korea));
        items.add(new Item("기욤 패트리","캐나다",R.drawable.flag_canada));
        items.add(new Item("타일러","미국",R.drawable.flag_usa));
        items.add(new Item("알베르토 몬디","이탈리아",R.drawable.flag_italy));
        items.add(new Item("샘 오취리","가나",R.drawable.flag_ghana));
        items.add(new Item("타쿠야","일본",R.drawable.flag_japan));
        items.add(new Item("전현무","대한민국",R.drawable.flag_korea));
        items.add(new Item("기욤 패트리","캐나다",R.drawable.flag_canada));
        items.add(new Item("타일러","미국",R.drawable.flag_usa));
        items.add(new Item("알베르토 몬디","이탈리아",R.drawable.flag_italy));
        items.add(new Item("샘 오취리","가나",R.drawable.flag_ghana));
        items.add(new Item("타쿠야","일본",R.drawable.flag_japan));
        // 5_ 그러면 대량의 데이터는 다 만들었다! 이제 화면의 아이템들이 어떻게 생겨먹은 애들인지 그걸 정해주러 가자.

        listView = findViewById(R.id.listview);
        adapter = new MyAdapter(items,this);
        listView.setAdapter(adapter);


        // 리스트뷰의 아이템을 클릭했을때 반응하기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = items.get(i);
                Toast.makeText(MainActivity.this, item.name, Toast.LENGTH_SHORT).show();
            }
        });
    }


}