package com.example.ex21_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 5_ 리스트뷰의 온 클릭리스너가 아니라 아이템클릭리스너임!!! 우리는 아이템을 누르는것이기 때문이지!
                // 6_ 세번째 파라미터 i : 클릭한 아이템의 인덱스 번호 : 0,1,2,3,4 ...
                // 7_ 토스트로 인덱스 번호 띄우기
                // Toast.makeText(MainActivity.this, i+"", Toast.LENGTH_SHORT).show();

                // 8_ 이번엔 글씨를 가져오자. 그러려면 우리는 arrays.xml 의 배열을 찾아와서,
                // 그리고 "datas" 라는 이름으로 작성된 String 배열을 가져오자.
                // res 폴더 안에 파일이 있기 때문에 res 폴더의 관리자를 먼저 소환해야 함!
                Resources res = getResources();
                String[] datas = res.getStringArray(R.array.datas);
                // 9_ 토스트로 글씨 띄워보자.
                Toast.makeText(MainActivity.this, datas[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}