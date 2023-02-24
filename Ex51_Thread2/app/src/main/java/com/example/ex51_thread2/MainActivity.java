package com.example.ex51_thread2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MyThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1_ 화면에 보이지 않더라도 별도 Thread 는 백그라운에서 동작하고 있다는것을 확인해보자!!
        findViewById(R.id.btn).setOnClickListener(view -> {
            thread = new MyThread();
            thread.start();
        });

    }

    class MyThread extends Thread {
        boolean isRun = true;

        @Override
        public void run() {
            // 2_ 5초마다 현재 시간을 Toast 로 보이도록 코드를 짜보자.
            while(isRun){

                // 3_ 현재 시간을 문자열로 만들자!
                Date now = new Date();
                String s = now.toString();

                // 5_ Toast 로 현재시간(s) 보여주기
                // ERROR!!  -  Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                        // 10_ 로그 기록 남기기
                        Log.i("Ex51",s);
                    }
                });
                // 4_ 5초간 잠재우기
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Log.i("Ex51","스레드 종료");
        }
    }
    // 6_ 액티비티가 메모리에서 없어질 때 자동으로 실행되는 콜백메서드 : onDestroy()

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 7_ 안드로이드 스튜디오의 [Logcat] 탭에 기록을 남길 수 있다. 이 기록을 log 라고 한다.
        Log.i("Ex51","onDestroy");

        thread.isRun = false;
    }

    // 8_ 디바이스의 [뒤로가기 버튼] 을 클릭했을 때 반응하는 콜백메소드 : onBackPressed()

    @Override
    public void onBackPressed() {
        // 9_  MainActivity를 뒤로가기를 눌러도 안꺼짐. 단지 안보일뿐이다!
        // 그래서 onBackPressed 메서드의 내부를 변경해서 강제로 액티비티를 꺼주었음.
        finish();

        // 11_ 근데 finish() 를 이용해서 메인액티비티를 종료했다고 해도 로그캣을 확인해보면,
        // 토스트메시지는 계속해서 띄워진다. 그 이유는 "프로세스" 가 죽지 않았기 때문이다. 메인 액티비티만 꺼질뿐.
        // 그래서 종료하려면 killProcess 라는 명령이 있긴하나, 권장하지 않는다. 앱 내 리소스 등이 안전하지 못할 수 있기 때문.

    }
}