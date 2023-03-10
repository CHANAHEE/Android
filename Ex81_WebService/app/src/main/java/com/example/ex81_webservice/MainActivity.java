package com.example.ex81_webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.example.ex81_webservice.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(view -> clickBtn());
        binding.btn2.setOnClickListener(view -> clickBtn2());
    }

    void clickBtn(){
        // 1_ 우리가 하고자 하는것은 웹서버에 접속하여 index.html 문서를 읽어와서 TextView 에 보여주기
        // 단, 네트워크 작업이므로 별도의 THread 가 해야하며, 퍼미션도 필요함.
        Thread t = new Thread(){
            @Override
            public void run() {
                // 2_ 서버에 접속하자. 그러기 위해 먼저 서버URL 을 만들자.
                String urlAddress = "http://tjdrjs0803.dothome.co.kr/index.html";

                // 3_ 무지개 로드 만들기
                try {
                    // 4_ 무지개로드를 열어주는 해임달객체를 생성.
                    URL url = new URL(urlAddress);

                    // 5_ 무지개로드를 열자 인제,
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    while(true){
                        String line = reader.readLine();
                        if(line == null) break;

                        buffer.append(line+"\n");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.tv.setText(buffer.toString());
                        }
                    });


                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t.start();
    }

    void clickBtn2(){
        // 6_ 웹서버의 이미지를 읽어오자. - 스레드와 스트림을 이용해야함. 근데 너무 짜증나서 안할거임. 라이브러리를 이용하자!
        // 이미지 로드 라이브러리를 이용하자. -> Glide or Picasso
        String urlAddress = "http://tjdrjs0803.dothome.co.kr/bg_one10.jpg";
        Glide.with(this).load(urlAddress).into(binding.iv);


    }
}