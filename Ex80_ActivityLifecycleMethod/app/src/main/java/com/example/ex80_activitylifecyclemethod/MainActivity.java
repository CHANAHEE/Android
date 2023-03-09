package com.example.ex80_activitylifecyclemethod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // 1_ Activity 클래스의 Lifecycle Method
    // 액티비티가 객체로 만들어져서 화면에 보여지고, 종료되어 메모리에서 사라질때 까지 상황에 따라 자동으로 실행되는 생명주기 콜백메소드. - 주요 6개 메소드

    // 2_ 첫째. 액티비티가 처음 메모리에 만들어질 때 자동으로 실행되는 메소드
    // 이 메소드가 실행되는 동안에는 어떤 UI 도 그려지지 않은 상태임.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
        Log.i("TAG","onCreate");
    }

    // 3_ 둘째. 액티비티의 뷰들이 보이기 시작할 때 자동으로 실행되는 메소드
    // 이 메소드가 실행중에는 터치해도 반응이 없다. Interaction 이 불가하다.
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG","onStart");
    }

    // 4_ 셋째. 액티비티가 완전히 보이고, 터치도 가능한 상태
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG","onResume");
    }

    ////////////////////////////////////////////////////////////////////
    // 5_ 위 3개의 메소드가 실행된 후 액티비티는 실행 중인 상태가 됨.
    // Activity 가 Running 중이다 라고 표현함.
    ///////////////////////////////////////////////////////////////////

    // 6_ 넷째. 어떤 이유에서든 액티비티가 화면에서 안보이기 시작할 때, 자동 실행되는 메소드
    // 화면에 UI 는 아직 보이지만, 터치는 안되는 상태가 됨. - 보통 이곳에서 스레드를 pause 함.
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG","onPause");
    }

    // 7_ 다섯째. 완전히 안보일 때 자동실행되는 메소드

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG","onStop");
    }

    //////////////////////////////////////////////////////////////////
    // 8_ 액티비티가 다른 액티비티에 의해 가려진 상태라면, 위 두 메소드만 발동한다.
    //////////////////////////////////////////////////////////////////

    // 9_ 폰의 뒤로가기 버튼이나 finish() 메소드로 액티비티를 종료했을 때,
    // 즉, 액티비티가 메모리에서 소멸되었을때 자동으로 실행되는 메소드
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG","onDestroy");
    }
    // 10_ Android 12 버전(API 31) 디바이스 부터는 처음 실행되는 액티비티는
    // 뒤로가기 버튼을 눌러도 finish 되지 않는다!
    // 빠르게 다시 실행하기 위해서!
    // 11_ 만약 뒤로가기 버튼으로 종료시키고 싶다면, 개발자가 onBackPressed() 를 재정의하여 finish() 를 호출하거나,
    // 최신 목록에서 스와이프로 제거해야한다.
}