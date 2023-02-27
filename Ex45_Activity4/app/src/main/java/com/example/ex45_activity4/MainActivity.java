package com.example.ex45_activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(view -> {
            // 1_ SecondActivity.class 명칭 없이 실행해보자 -> 묵시적 인텐트를 활용하자!
            // 응당 사진갤러리라면 어떠한 식별자를 가져야한다! 즉, 세컨액티비티도 응당 어떠한 식별자를 가질 수 있다!
            // 이 설정은 메니페스트 파일에서 해준다!

            // 3_ 묵시적 인텐트 만들기
            Intent intent = new Intent();
            //intent.setAction("aaaa");
            intent.setAction("aaaa"); // 메니페스트의 속성인 action 을 여러개 줄 수도 있음.
            // 명시적 인텐트를 사용하려면 같은 앱 내부에서만 사용이 가능하다.
            // 다른 사람이 만든 앱을 사용하려면 묵시적 인텐트를 사용해주어야 한다!!!
            startActivity(intent);
        });
    }
}