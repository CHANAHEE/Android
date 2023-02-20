package com.example.ex30_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 2_ 참조변수 만들기
    TextView tv;
    Button btn,btn2;
    MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 3_ 참조변수에 xml 에서 만든 뷰객체 id 를 받아와서 뷰를 제어할 준비하기
        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);


        // 4_ 버튼 클릭 이벤트
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("nice to meet you");
            }
        });

        // 15_ 버튼 클릭 이벤트2
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 16_ MyFragment 객체를 찾아서 참조하자.
                // 17_ 메인액티비티가 프래그먼트까지 관리하기 너무 힘드니, 프래그먼트와 관련한 매니저 객체를 따로 만들어줬다!
                // 18_ 그래서 Fragment 를 관리하는 관리자 객체를 소환해야한다 : FragmentManager
                FragmentManager manager = getSupportFragmentManager(); // 19_ 서포트버전으로 사용해줘야한다! 우리는 새 프래그먼트를 사용하니까!!
                myFragment = (MyFragment) manager.findFragmentById(R.id.fragment);
                myFragment.tv.setText("WOW~~~");
            }
        });
    }
}