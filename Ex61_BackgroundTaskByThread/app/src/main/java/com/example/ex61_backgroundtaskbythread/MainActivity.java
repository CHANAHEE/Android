package com.example.ex61_backgroundtaskbythread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(view -> clickStart());
        findViewById(R.id.btn_stop).setOnClickListener(view -> clickStop());
    }

    // 10_ 디바이스의 뒤로가기 버튼을 클릭했을 때, 반응하는 콜백메소드가 있다.
    // 11_ 기본 메인액티비티가 종료되지 않기때문에 finish() 를 이용해 강제로 종료시키자.
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }

    void clickStart(){
        // 1_ 백그라운드에서 반복작업을 수행하는 스레드 생성
        // 4_ 버튼을 여러번 눌러 객체가 여러번 생성되면 안되니 조건을 달자.
        if(thread != null) return;

        thread = new MyThread();
        thread.start(); // 5_ 자동 run() 가 발동
    }
    void clickStop(){
        if(thread != null) {
            // 6_ Thread 는 run 메소드가 종료되면 멈춤. 1회성 객체라서 다시 실행 불가. 다시 start 가 안된다. 다시하려면 새로운 객체를 만들어야함.
            // 7_ while 문 때문에 run 메소드가 종료되지 않기 때문에 while 문의 조건값을 변경시켜 run 메소드를 종료시키자.
            thread.isRun = false;
            thread = null; // 8_ null 을 안넣으면 다시 start 할 수 없다. 꼭 넣어주자. 그리고 이미 생성된 객체는 가비지컬렉터가 수거해간다.
        } else {

            Toast.makeText(this, "Thread 객체를 참조하고 있지 않습니다.", Toast.LENGTH_SHORT).show();

        }
    }

    // 2_ 백그라운드 동작을 하는 별도 THread 클래스를 설계
    class MyThread extends Thread{
        boolean isRun = true;
        @Override
        public void run() {

            while (isRun){
                // 9_ new Runnable 도 람다식으로 줄일 수 있음.
                runOnUiThread(()->{
                    Toast.makeText(MainActivity.this, "백그라운드 작업중...", Toast.LENGTH_SHORT).show();
                    Log.i("Ex61","백그라운드 작업중...");
                });
                // 3_ 5초 정도 대기하자.
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}