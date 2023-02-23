package com.example.ex42_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1_ 두번째 화면(SecondActivity) 으로 이동하기 위해 화면부터 만들자

                // 6_ SecondActivity 를 실행시켜 줄 택배기사 객체를 생성해보자, 첫번째 파라미터로는 인텐트가 있는곳의 Context 객체를,
                // 두번째 파라미터로는 실행시킬 액티비티의 클래스 정보가 담긴 객체를
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // 7_ 택배기사를 통해 새로운 액티비티를 실행
                startActivity(intent);

                // 8_ 세컨액티비티가 실행되면 화면에는 세컨액티비티가 띄워진다. 그러면 메인액티비티는 BackStack 에 저장된다.
                // 백스택은 스택 자료구조로 되어있다. 그래서 세컨액티비티를 종료시키면 메인액티비티가 띄워진다.

                // 9_ 현재 액티비티를 종료
                finish();
            }
        });

        btn2 = findViewById(R.id.btn2);

        // 10_ 리스너의 추상메소드를 구현하자니 너무 짜증~ 심지어 구현할 메소드가 하나일때도 똑같이 작성해야하니 더 짜증~
        // 그래서 구현할 메소드가 하나라면, 람다식이라는 익명클래스의 축약표현 문법을 사용할 수 있다!!
        btn2.setOnClickListener( view -> {
            // 11_ ThirdActivity 를 실행시켜보자! 그러려면 실행시켜줄 택배기사 객체를 생성해야한다.
            Intent intent = new Intent(this, ThirdActivity.class);
            // 12_ 근데 축약이긴하지만 익명클래스는 생략된것임. 그렇기 때문에 역재 이 메소드를 감싸고 있는 클래스는 익명클래스가 아닌 MainActivity!!!
            startActivity(intent);
        });
    }
}