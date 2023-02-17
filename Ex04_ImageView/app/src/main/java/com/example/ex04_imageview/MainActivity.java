package com.example.ex04_imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    Button btn01,btn02,btn03,btn04;
    int start = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.flag);
        btn01 = findViewById(R.id.btn01);
        btn02 = findViewById(R.id.btn02);
        btn03 = findViewById(R.id.btn03);
        btn04 = findViewById(R.id.btn04);


        btn01.setOnClickListener(listener);
        btn02.setOnClickListener(listener);
        btn03.setOnClickListener(listener);
        btn04.setOnClickListener(listener);

        // 9_ 이미지뷰를 차례대로 변경
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                iv.setImageResource(R.drawable.flag_belgium);
//            }
//        });
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) { // 7_ 요 파라미터 view : 클릭한 버튼을 참조하는 참조변수!!
            // 6_ 어떤 뷰(버튼) 을 클릭하였는지 알아내기
            int id = view.getId();

            // 8_ Id 에 따라 해당 버튼의 기능을 구현.
            if( id == R.id.btn01) iv.setImageResource(R.drawable.flag_korea);
            else if( id == R.id.btn02) iv.setImageResource(R.drawable.flag_belgium);
            else if( id == R.id.btn03) iv.setImageResource(R.drawable.flag_germany);
            else if( id == R.id.btn04) iv.setImageResource(R.drawable.flag_usa);




        }
    };

}