package com.example.ex28_recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 3_ 대량의 데이터
    ArrayList<Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter adapter;
    Button addBtn,deleteBtn,linearBtn,gridBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 4_ 대량의 데이터 만들기 - 실무에서는 DB 혹은 서버에서 데이터를 읽어옴.
        items.add(new Item("루피","해적단의 선장",R.drawable.crew_luffy,R.drawable.bg_one01));
        items.add(new Item("조로","해적단의 부선장",R.drawable.crew_zoro,R.drawable.bg_one02));
        items.add(new Item("나미","해적단의 항해사",R.drawable.crew_nami,R.drawable.bg_one03));
        items.add(new Item("우솝","해적단의 저격수",R.drawable.crew_usopp,R.drawable.bg_one04));
        items.add(new Item("상디","해적단의 요리사",R.drawable.crew_sanji,R.drawable.bg_one05));
        items.add(new Item("초파","해적단의 의사",R.drawable.crew_chopper,R.drawable.bg_one06));
        items.add(new Item("니코로빈","해적단의 역사가",R.drawable.crew_nicorobin,R.drawable.bg_one07));
        // 5_ 이런것들을 xml 에서 하나하나 뷰를 만들기는 짜증~ 이걸 대신해주는게 어댑터!! 그러면 어떤 모양으로 리사이클러뷰의 아이템을 만들지 정하러가보자. 그래야 어댑터한테 주지.


        // 13_ 이제 리사이클러뷰에 어댑터를 붙여주자.
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,items);
        recyclerView.setAdapter(adapter);

        // 14_ 리사이클러뷰는 아이템 클릭 리스너 처리가 없음. 그래서 어댑터에서 아이템뷰에 직접 클릭 리스너를 설정해주어야 한다!!!

        addBtn = findViewById(R.id.btn_add);
        deleteBtn = findViewById(R.id.btn_delete);
        linearBtn = findViewById(R.id.btn_linear);
        gridBtn = findViewById(R.id.btn_grid);

        // 17_ 버튼 리스너 만들기
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 18_ 대량의 데이터의 리스트에 데이터를 추가해야한다! 리사이클러 뷰가 아님~
                items.add(0,new Item("new1","해적단의 신입",R.drawable.bg_one08,R.drawable.bg_one09));

                // 19_ 데이타셋체인지는 어댑터에게 데이터의 변경이 전체가 다 이뤄졌다고 notify 한다. 이러면 리소스의 낭비가 심하다.
                // 이거는 보통 뭐 전체삭제 같은 기능에서 쓰면 좋다.
                adapter.notifyDataSetChanged();

                // 20_ 데이터 아이템 1개가 추가되었다고 공지해보자.
                adapter.notifyItemInserted(0);
                // 21_ 스크롤은 안올라간다.. 위치 조정해줘야겠네
                recyclerView.scrollToPosition(0);
            }
        });

        // 22_ 아이템 리스트에서 첫번째 데이터를 제거
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                items.remove(0);
            }

        });

        // 23_ 리사이클러뷰의 레이아웃 매니저를 새로이 선정
        linearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        gridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
                recyclerView.setLayoutManager(gridLayoutManager);
            }
        });
    }
}