package com.example.ex49_threadnetworkimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btn,btn2;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);
        iv = findViewById(R.id.iv);

        btn.setOnClickListener(view -> { clickBtn();});
        btn2.setOnClickListener(view -> {
            // 13_ 이미지 로드 라이브러리(Glide , Picasso) 사용해보기
            String imgUrl = "https://cdn.pixabay.com/photo/2023/01/05/22/35/flower-7700011_640.jpg";
            Glide.with(this).load(imgUrl).into(iv);

        });
    }

    void clickBtn(){
        // 1_ 네트워크 상에 있는 이미지를 읽어와서 이미지뷰에 띄워주는 작업을 처리.
        // 근데, 네트워크 작업은 오래 걸리는 작업으로 인식하기 때문에 반드시 별도의 Thread 작업을 통해 처리해주어야 한다!!

        // 8_ 주의할 점 !!!!!! : 네트워크 작업은 반드시 퍼미션을 받아야한다. [ AndroidManifest.xml ] 에서 작업해주어야 한다. 메니페스트 파일로 go~
        new Thread(){
            @Override
            public void run() {
                // 2_ 네트워크 작업은 스트림을 이용해야한다! 그리고 그 스트림을 만들어주는 해임달이 필요~
                String imgUrl ="https://cdn.pixabay.com/photo/2022/06/07/15/56/child-7248693__340.jpg";

                // 3_ 서버 주소까지 연결되는 무지개로드를 열자. 그러기 위해서 스트림을 열 수 있는 해임달 객체(URL) 를 생성
                try {
                    URL url = new URL(imgUrl);

                    // 4_ 무지개 로드 열기
                    InputStream is = url.openStream();

                    // 5_ 원래 이미지는 바이트의 배열이다. 그래서 불러오려면 바이트를 읽어오는 작업을 해야하는데, 안드로이드에서 이걸 해준다.
                    // 6_ 스트림을 통해 이미지를 읽어와서 Bitmap 객체로 생성하자. 비트맵은 .png 등의 이미지 파일을 자바코드에서 사용하기 위해 사용하는 객체. 안드로이드에서 그림은 비트맵만이 가질 수 있다.
                    Bitmap bm = BitmapFactory.decodeStream(is);

                    // 7_ 이미지뷰에 비트맵을 설정하자.
                    // iv.setImageBitmap(bm);

                    // 11_ UI 작업은 메인스레드 위에서 해야하므로, runOnUiThread 메서드를 실행시키자.
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bm);
                        }
                    });
                    is.close();


                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }.start();
    }
}