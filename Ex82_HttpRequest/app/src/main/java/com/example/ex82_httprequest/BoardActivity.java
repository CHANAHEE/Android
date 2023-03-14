package com.example.ex82_httprequest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.ex82_httprequest.databinding.ActivityBoardBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    // 29_ 일단은 뷰바인딩 해주고, 리사이클러뷰를 위한 준비를 하나하나 하자.


    ActivityBoardBinding binding;

    // 31_ 대량의 데이터를 담을 배열 준비. 이제 아이템 시안 준비하러가자.
    ArrayList<Item> items = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_board);
        binding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new MyAdapter(this,items);
        binding.recyclerview.setAdapter(adapter);
    }

    // 34_ 화면이 생성되서 화면에 보여질때! 우리는 데이터를 가져올거임.
    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // 35_ 서버에서 데이터를 가져오는 메서드!
    }

    void loadData(){
//        // 36_ 테스트 데이터 추가해보기
//        items.add(new Item(1,"aaa","aaaaa","2023"));
//        items.add(new Item(2,"bbb","bbbbb","2024"));
//        adapter.notifyDataSetChanged();
// 37_ 잘 나오는거 확인했으니, 이제 서버에서 실제 데이터를 불러오자.

        new Thread(){
            @Override
            public void run() {
                // 38_ 서버 DB 값을 echo 해주는 php 문서를 실행
                String serverAddress = "http://tjdrjs0803.dothome.co.kr/Android/loadDB.php";
                try {
                    URL url = new URL(serverAddress);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    while(true){
                        String line = reader.readLine();

                        if(line == null) break;
                        Log.i("12345", line);
                        buffer.append(line +"\n");
                    }
                    // 39_ 잘 읽어왔는지 확인 용으로 ..
                    // 40_ 그럼 이제 loadDB.php 파일 만들러가자~
//                    runOnUiThread(()->{
//                        new AlertDialog.Builder(BoardActivity.this).setMessage(buffer.toString()).create().show();
//                    });

                    // 47_ 이제 서버에서 echo 된 문자열 데이터에서 & 을 기준으로, 문자열을 분리하자.
                    // 한줄 단위(Item) 으로 데이터를 분리...
                    String[] rows = buffer.toString().split("&");
                    Log.i("12345", rows.length+"");

                    // 48_ 한줄 데이터의 콤마구분자를 분리하여 값을 분류하자.
                    for(String row : rows){
                        String[] datas = row.split(",");
                        if(datas.length != 4) continue;

                        int no = Integer.parseInt(datas[0]);
                        String name = datas[1];
                        String msg = datas[2];
                        String date = datas[3];

                        items.add(new Item(no,name,msg,date));
                    }
                    runOnUiThread(()->{
                        adapter.notifyDataSetChanged();
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