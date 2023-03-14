package com.example.ex82_httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ex82_httprequest.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGet.setOnClickListener(view -> clickGet());
        binding.btnPost.setOnClickListener(view -> clickPost());
        // 28_ DB 연결종료하고 여기로 왔음~ 버튼 누르면 다른 액티비티로 갈꺼임.
        // 그 액티비티에서 리사이클러뷰로 데이터베이스에서 가져온 데이터 뿌려줄예정.
        binding.btnLoad.setOnClickListener(view -> {
            Intent intent = new Intent(this, BoardActivity.class);
            startActivity(intent);
        });
    }


    void clickGet(){
        // 1_ 네트워크 작업을 해야하므로 네트워크 작업용 스레드를 만들자.
        new Thread(){
            @Override
            public void run() {

                // 2_ 서버로 보낼 데이터를 가져오자.
                String name = binding.etName.getText().toString();
                String msg = binding.etMessage.getText().toString();

                // 3_ GET 방식으로 보낼 서버의 주소가 필요하다!
                String serverAddress = "http://tjdrjs0803.dothome.co.kr/Android/getTest.php";

                // 4_ GET 방식은 URL 뒤에 붙여 정보(name,msg)를 보내주어야 한다.
                // 보안에 취약하고 특수문자 안되고, 글자수의 제한이있다~ 하지만 속도는 빠름.
                // 5_ 단, URL 에는 한글 및 특수문자 사용 불가 - 한글을 URL 에 사용될 수 있도록 암호화(인코딩) 해주어야 한다.
                // 그리고 인코딩을 해주는 클래스가 이미 있다!
                try {
                    name = URLEncoder.encode(name,"UTF-8");
                    msg = URLEncoder.encode(msg,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                String getAddress = serverAddress + "?name=" + name + "&msg=" + msg;

                // 6_ 이제 주소는 다 만들어졌다. 서버와 연결하는 작업을 해주자.
                try {
                    URL url = new URL(getAddress);
                    // 7_ url.openStream(); -> URL 은 InputStream 만 열 수 있게 된다.
                    // 8_ OutputStream 까지 하는 Http 통신용 객체를 이용해야 함.
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    // 9_ connection 에게 4가지 설정을 해주어야 함.
                    connection.setRequestMethod("GET"); // 10_ GET 방식을 할꺼다~
                    connection.setDoInput(true); // 11_ echo 응답을 받아와야 하니 Input 이 필요하다! URL 에 정보가 다들어있으니, Output 은 필요없음!
                    connection.setUseCaches(false); // 12_ 캐시를 쓰지 말라고 설정해주어야 함. 구글의 권장사항.

                    // 13_ GET 방식은 이미 URL 에 데이터가 추가되어 있어서 별도로 OutputStream 이 필요하지 않다.
                    // 14_ 서버로 부터 응답(Response) 된 결과를 받아보기 위해...
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    while(true){
                        String line = reader.readLine();
                        if(line == null) break;
                        buffer.append(line + "");
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
        }.start();
    }
    void clickPost(){
        new Thread(){
            @Override
            public void run() {
                // 18_ 서버로 보낼 데이터들
                String name = binding.etName.getText().toString();
                String message = binding.etMessage.getText().toString();

                // 19_ POST 방식으로 데이터를 보낼 서버 주소를 준비하자.
                String serverAddress = "http://tjdrjs0803.dothome.co.kr/Android/postTest.php";

                try {
                    URL url = new URL(serverAddress);
                    // 20_ Http 통신용 객체를 만들기
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);

                    // 21_ 보낼 데이터들을 POST 방식으로 쓰기 위해 키-밸류 규칙에 맞게 하나의 문자열로 결합하자.
                    String data = "name="+name+"&msg="+message;

                    // 22_ 데이터를 OutputStream 을 이용하여 직접 내보내기
                    OutputStream os = connection.getOutputStream();
                    OutputStreamWriter writer = new OutputStreamWriter(os);

                    writer.write(data,0,data.length());
                    writer.flush();
                    writer.close();

                    // 23_ 여기까지 했으면 일단 서버로 데이터를 보낸거임. 서버에서 echo 한 응답 문자열 읽어오자.
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    while(true){
                        String line = reader.readLine();
                        if(line == null) break;

                        buffer.append(line + "\n");
                    }
                    runOnUiThread(()->{
                        binding.tv.setText(buffer.toString());
                    });
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
}