package com.example.ex33_toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 4_ 툴바는 굳이 멤버변수로 참조변수를 만들 필요는 X

        // 5_ 툴바를 찾아와서 액션바로 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 6_ 이렇게 해주면 제목줄로 대체된것을 볼 수 있다.


        // 14_ 액션바의 제목글씨를 지워보자! 근데 툴바한테 명령을 내리면 안된다.
        // 툴바는 액션바의 모든 기능을 가지고 있지않다! 대신 툴바가 액션바로 대체된것.
        ActionBar actionBar = getSupportActionBar(); // 이렇게 하면 액션바 참조변수에ㅔ 담긴 액션바는 결국 툴바!
        actionBar.setDisplayShowTitleEnabled(false);
    }

    // 8_ 옵션메뉴 만들기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option,menu);
        return super.onCreateOptionsMenu(menu);
    }
    // 9_ 이렇게 옵션메뉴를 만들어서 붙일수 있다는건 툴바가 제목줄의 역할을 한다는것.
}