package com.example.tp02_1to25;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button[] btnArr = new Button[25];
    Button btn;
    TextView tv;
    Button retryBtn;

    int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// 랜덤클래스를 이용해서 해도 되지만... ArrayList 의 메서드를 써보자!
//        Random rand = new Random();
//        int[] ranNum = new int[25];
//
//        for(int i = 0;i<btnArr.length;i++){
//            ranNum[i] = rand.nextInt(25)+1;
//            for(int k =0;k<i;k++){
//                if(ranNum[k] == ranNum[i]){
//                    i--;
//                    break;
//                }
//            }
//        }
//        for(int i = 0; i<btnArr.length;i++){
//            btn = findViewById(R.id.btn1+i);
//            btn.setText(ranNum[i] + "");
//        }

        tv = findViewById(R.id.tv);
        ArrayList<Integer> nums = new ArrayList<>();
        for(int n = 1; n<=25 ; n++) nums.add(n);
        Collections.shuffle(nums);

        for(int i=0;i<nums.size();i++){
            btnArr[i] = findViewById(R.id.btn1+i);
            btnArr[i].setText(nums.get(i) + "");
            btnArr[i].setOnClickListener(listener);
            btnArr[i].setTag(nums.get(i)); // 요술주머니 Tag!!
        }


    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) { // 파라미터 view : 클릭된 버튼 참조변수 - 업캐스팅
            // view.getText() : 자식의 고유기능은 사용할 수 없음!
//            Button btn = (Button) view; // 그렇기 때문에 형변환 하여 사용해주어야 한다! - 다운캐스팅 왜? 버튼의 고유기능인 getText() 를 사용하기 위하여!!
//            String s = btn.getText().toString();
//            int n = Integer.parseInt(s);

            // 버튼뷰에 저장된 Tag 값을 읽어와서 num 과 같은지 비교 이미지 등을 활용할 때 이렇게 쓸 수 있음.
            String s= view.getTag().toString();
            int n = Integer.parseInt(s);
            Button btn = (Button) view;
            if(n == num){
                btn.setText("OK");
                btn.setTextColor(Color.parseColor("#FF0000"));
                btn.setBackground(null); // 버튼은 배경이 그림임.(그림을 없앤것임)

                num++;
                tv.setText(num+"");


            }
            if(num>25){
                tv.setText("STAGE CLEAR");
                retryBtn.setVisibility(View.VISIBLE);
            }

        }
    };




}