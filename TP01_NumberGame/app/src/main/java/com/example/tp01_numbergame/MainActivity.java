package com.example.tp01_numbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button[] btnArr = new Button[10];
    TextView num1,num2,num3,strike,ball,out,gameResult,round;
    int count=0, roundCount = 1, strikeNum=0, ballNum=0, outNum=0;
    int answer100,answer10,answer1;
    Random rand = new Random();

    Button eraseBtn, inputBtn,retryBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 버튼 배열 id 대입
        for(int i = 0; i < 10 ; i++){
            btnArr[i] = findViewById(R.id.btn0++);
        }
        // 뷰 객체 id 대입
        eraseBtn = findViewById(R.id.btn_back);
        inputBtn = findViewById(R.id.btn_input);
        retryBtn = findViewById(R.id.btn_retry);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);

        strike = findViewById(R.id.strike);
        ball = findViewById(R.id.ball);
        out = findViewById(R.id.out);

        gameResult = findViewById(R.id.gameResult);
        round = findViewById(R.id.round);


        // 정답 만들기
        setAnswer();

        round = findViewById(R.id.round);
        round.setText( roundCount +"");
        round.setVisibility(View.VISIBLE);


        for(int i = 0; i< 10; i++){
            btnArr[i].setOnClickListener(listener);
        }
        inputBtn.setOnClickListener(listener_input);
        eraseBtn.setOnClickListener(listener_erase);
        retryBtn.setOnClickListener(listener_retry);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            count++;
            setNumber(btn);
            allNumChecked(count);
        }
    };
//    private View.OnClickListener listener1 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            count++;
//            setNumber(btnArr[1]);
//            allNumChecked(count);
//        }
//    };
//    private View.OnClickListener listener2 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            count++;
//            setNumber(btnArr[2]);
//            allNumChecked(count);
//        }
//    };
//    private View.OnClickListener listener3 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            count++;
//            setNumber(btnArr[3]);
//            allNumChecked(count);
//        }
//    };
//    private View.OnClickListener listener4 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            count++;
//            setNumber(btnArr[4]);
//            allNumChecked(count);
//        }
//    };
//    private View.OnClickListener listener5 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            count++;
//            setNumber(btnArr[5]);
//            allNumChecked(count);
//        }
//    };
//    private View.OnClickListener listener6 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            count++;
//            setNumber(btnArr[6]);
//            allNumChecked(count);
//        }
//    };
//    private View.OnClickListener listener7 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            count++;
//            setNumber(btnArr[7]);
//            allNumChecked(count);
//        }
//    };
//    private View.OnClickListener listener8 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            count++;
//            setNumber(btnArr[8]);
//            allNumChecked(count);
//        }
//    };
//    private View.OnClickListener listener9 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            count++;
//            setNumber(btnArr[9]);
//            allNumChecked(count);
//        }
//    };
    private View.OnClickListener listener_input = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            count = 0;
            try {
                String s1 = num1.getText().toString();
                int n100 = Integer.parseInt(s1);

                String s2 = num2.getText().toString();
                int n10 = Integer.parseInt(s2);

                String s3 = num3.getText().toString();
                int n1 = Integer.parseInt(s3);


                if(n100 == answer100) strikeNum++;
                else if(n100 == answer10) ballNum++;
                else if(n100 == answer1) ballNum++;
                else outNum++;

                if(n10 == answer100) ballNum++;
                else if(n10 == answer10) strikeNum++;
                else if(n10 == answer1) ballNum++;
                else outNum++;

                if(n1 == answer100) ballNum++;
                else if(n1 == answer10) ballNum++;
                else if(n1 == answer1) strikeNum++;
                else outNum++;

            }catch (Exception e){
                if(roundCount != 0) roundCount--;
                Toast.makeText(getApplicationContext(),"숫자를 입력해주세요",Toast.LENGTH_SHORT).show();
            }

            if(strikeNum == 3) {
                gameResult.setVisibility(View.VISIBLE);
                for(int i = 0; i < 10; i++){
                    btnArr[i].setEnabled(false);
                    btnArr[i].setBackgroundColor(Color.parseColor("#FF555950"));
                    btnArr[i].setTextColor(Color.parseColor("#FF2C2B2B"));
                }
                eraseBtn.setEnabled(false);
                inputBtn.setEnabled(false);
                retryBtn.setVisibility(View.VISIBLE);
            }

            init();
            }
    };
    private View.OnClickListener listener_erase = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!num3.getText().equals("")) num3.setText("");
            else if(!num2.getText().equals("")) num2.setText("");
            else num1.setText("");
            count--;

            for(Button b : btnArr){
                b.setBackgroundColor(Color.parseColor("#9CB580"));
                b.setTextColor(Color.parseColor("#FFFFFF"));
                b.setEnabled(true);
            }

            for(int i = 0; i< 10;i ++){
                if(btnArr[i].getText().equals(num2.getText())){
                    btnArr[i].setBackgroundColor(Color.parseColor("#FF555950"));
                    btnArr[i].setTextColor(Color.parseColor("#FF2C2B2B"));
                    btnArr[i].setEnabled(false);
                }
                else if(btnArr[i].getText().equals(num1.getText())){
                    btnArr[i].setBackgroundColor(Color.parseColor("#FF555950"));
                    btnArr[i].setTextColor(Color.parseColor("#FF2C2B2B"));
                    btnArr[i].setEnabled(false);
                }
            }

        }
    };
    View.OnClickListener listener_retry = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            gameResult.setVisibility(View.GONE);
            for(int i = 0; i < 10; i++){
                btnArr[i].setEnabled(true);
                btnArr[i].setBackgroundColor(Color.parseColor("#9CB580"));
                btnArr[i].setTextColor(Color.parseColor("#FFFFFF"));
            }
            eraseBtn.setEnabled(true);
            inputBtn.setEnabled(true);

            strike.setText("0");
            roundCount = 1;
            round.setText("1");
        }
    };

    public void init(){

        for(int i = 0;i<10;i++){
            btnArr[i].setEnabled(true);
            btnArr[i].setBackgroundColor(Color.parseColor("#9CB580"));
            btnArr[i].setTextColor(Color.parseColor("#FFFFFF"));
        }
        roundCount++;
        round = findViewById(R.id.round);
        round.setText( roundCount +"");
        round.setVisibility(View.VISIBLE);


        num1.setText("");
        num2.setText("");
        num3.setText("");

        strike.setText(String.valueOf(strikeNum));
        ball.setText(String.valueOf(ballNum));
        out.setText(String.valueOf(outNum));

        strikeNum = 0;
        ballNum = 0;
        outNum = 0;

    }
    public void setNumber (Button btn){
        if(num1.getText().equals("")) num1.setText(btn.getText());
        else if(num2.getText().equals("")) num2.setText(btn.getText());
        else num3.setText(btn.getText());
        btn.setBackgroundColor(Color.parseColor("#FF555950"));
        btn.setTextColor(Color.parseColor("#FF2C2B2B"));
        btn.setEnabled(false);
    }

    public void allNumChecked(int count){
        if(count == 3){
            for(int i = 0; i < 10; i++){
                btnArr[i].setEnabled(false);
                btnArr[i].setBackgroundColor(Color.parseColor("#FF555950"));
                btnArr[i].setTextColor(Color.parseColor("#FF2C2B2B"));
            }

        }
//        else{
//            for (int i = 0; i< 10; i++){
//                btnArr[i].setEnabled(true);
//                btnArr[i].setBackgroundColor(Color.parseColor("#9CB580"));
//                btnArr[i].setTextColor(Color.parseColor("#FFFFFF"));
//            }
//        }

    }

    public void setAnswer(){

        answer100 = rand.nextInt(10);
        while(answer100 == answer10) answer10 = rand.nextInt(10);
        while(answer100 == answer1 || answer10 == answer1) answer1 = rand.nextInt(10);

    }
}



