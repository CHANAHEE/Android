package com.example.ex08_framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    LinearLayout layout1,layout2,layout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.newyork);
        btn2 = findViewById(R.id.paris);
        btn3 = findViewById(R.id.sydney);

        layout1 = findViewById(R.id.layout01);
        layout2 = findViewById(R.id.layout02);
        layout3 = findViewById(R.id.layout03);

        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) { // 파라미터 view : 클린된 버튼 참조변수
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);

            int id = view.getId();
            
            if( id == R.id.newyork) layout1.setVisibility(View.VISIBLE);
            else if( id == R.id.paris) layout2.setVisibility(View.VISIBLE);
            else layout3.setVisibility(View.VISIBLE);

//            if(id == R.id.newyork) { // 이렇게 작성하니 좀.. 지저분하다.
//                layout1.setVisibility(View.VISIBLE);
//                layout2.setVisibility(View.GONE);
//                layout3.setVisibility(View.INVISIBLE);
//            }
//            else if(id == R.id.paris) {
//                layout1.setVisibility(View.INVISIBLE);
//                layout2.setVisibility(View.VISIBLE);
//                layout3.setVisibility(View.INVISIBLE);
//            } else {
//                layout1.setVisibility(View.INVISIBLE);
//                layout2.setVisibility(View.INVISIBLE);
//                layout3.setVisibility(View.VISIBLE);
//            }
        }
    };
}