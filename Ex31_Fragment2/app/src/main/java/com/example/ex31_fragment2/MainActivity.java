package com.example.ex31_fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // 4_ 참조변수랑 리스너 만들기
    Button btn;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 8_ 프래그먼트 관리자 소환
        manager = getSupportFragmentManager();
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 7_ 아이디가 container_fragment 인 뷰그룹에 MyFragment 를 붙이자.
                // 9_ 프래그먼트 동적작업 (add, remove, replace) 시작
                // 10_ 그냥 붙이면 중간에 뻑이나거나 하는 상황을 대비하기 위해 트랜잭션 과정이 필요하다.
                // 그리고 이러한 작업을 해주는 놈이 필요하다.
                FragmentTransaction tran = manager.beginTransaction();

                // 11_ 동적작업 시작
                tran.add(R.id.container,new Fragment());

                // 12_ 뒤로가기 버튼을 눌렀을 때 프래그먼트 추가하기 이전상태로 돌아가려면..
                tran.addToBackStack(null);

                tran.commit();
            }
        });
    }
}