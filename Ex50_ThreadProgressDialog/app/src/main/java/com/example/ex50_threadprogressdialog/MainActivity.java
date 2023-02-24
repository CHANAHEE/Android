package com.example.ex50_threadprogressdialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(view -> clickBtn()); // 1_ 실행문이 한줄이라면!! 중괄호와 세미콜론 생략가능!
        findViewById(R.id.btn2).setOnClickListener(view -> clickBtn2());
    }

    // 2_ 혹시 스레드 때매 다이얼로그가 띄워졌는데 또 띄울까봐 그걸 방지해보자
    ProgressDialog dialog;
    int gauge = 0; // 6_ 막대바 게이지 변수
    public void clickBtn(){
        // wheel type progress dialog..
        if(dialog != null) return; // 3_ 다이얼로그가 띄워져있니? 그럼 return 해서 메소드를 종료해!!

        dialog = new ProgressDialog(this);
        dialog.setTitle("Wheel Dialog");
        dialog.setMessage("downloading...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        // 4_ 다이얼로그를 띄우고, 3초뒤에 없애고 싶다면?! Thread.sleep(3000) 을 사용하면
        // 메인스레드가 3초동안 잠자는거임!!! 그러니까 핸들러를 이용하자!
        handler.sendEmptyMessageDelayed(0,3000);
    }

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            dialog.dismiss();
            dialog = null;
        }
    };


    // 5_ 이번에는 스피너 타입말고 수평선 타입으로 해보자!
    public void clickBtn2(){

        if(dialog != null) return;

        dialog = new ProgressDialog(this);
        dialog.setTitle("Bar Dialog");
        dialog.setMessage("downloading...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMax(50);
        dialog.show();

        // 7_ 막대바의 gauge 값을 증가시키는 별도의 Thread
        new Thread(){
            @Override
            public void run() {
                gauge = 0;
                while(gauge < 50){
                    gauge++;
                    dialog.setProgress(gauge); // 8_ 정말 다행스럽게도 다이얼로그 내부적으로 runOnUiThread() 가 실행된다!
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                dialog.dismiss();
                dialog = null;
            }
        }.start();
    }
}