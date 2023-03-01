package com.example.ex48_thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn,btn2;
    TextView tv;

    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);
        tv = findViewById(R.id.tv);

        btn.setOnClickListener(view -> {
            // 1_ 오래 걸리는 작업을 해보자 [네트워크 작업, DB 작업 등등]
            for (int i=0; i<20;i++){
                // 2_ 반복문이 돌 때마다 스레드를 1초간 잠재우자.
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 3_ 텍스트뷰에 숫자를 카운트해서 보기좋게 하자.
                num++;
                tv.setText(num+"");
            }
        });

        btn2.setOnClickListener(view -> {
            // 4_ 오래 걸리는 작업을 별도의 Thread 에게 수행하도록 해보자! 그럼일단 Thread 를 만들어야 겠군
            MyThread thread = new MyThread();
            thread.start(); // 9_ Thread 의 작업 시작 명령! - 이 Thread 클래스의 run 메소드가 실행된다!
        });
    }

    // 5_ 오래 걸리는 작업을 수행할 Thread 의 작업내역을 설계해보자
    class MyThread extends Thread {

        // 6_ run 메소드안에 작업내역을 설계해야한다!
        @Override
        public void run() {
            for (int i=0; i<20;i++){
                // 12_ 핸들러 객체 이용
                num++;
                //handler.sendEmptyMessage(0); // 13_ 파라미터로 메시지의 식별번호를 줘야한다.
                // 15_ 아래위로 왔다갔다 해야함. 귀찮음 그래서 두번째 방식이 많이 사용됨.

                // 16_ 두번째 방식
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 이 영역에서는 UI 작업이 가능하다.
                        tv.setText(num+"");
                    }
                }); // 17_ MainActivity 위라서 바로 사용가능!

                // 7_ 반복문이 돌 때마다 스레드를 1초간 잠재우자.
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 8_ 텍스트뷰에 숫자를 카운트해서 보기좋게 하자.
                // tv.setText(i+"");
                // 10_ 이게 에러가 난다. UI 를 변경하는 작업은 Main Thread 만 하도록 강제하기 때문이다.
            }
        }
    }

    // 11_ 별도 스레드가 메인스레드에게 UI 변경작업을 요청할 때 그 메시지를 전달하는 역할을 하는 핸들러 객체 생성
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            // 14_ sendMessage() 메서드가 실행되어 메시지가 전달되면 자동으로 실행되는 영역
            // 이 영역에서는 UI 작업이 가능하다!
            tv.setText(num+"");
        }
    };
}