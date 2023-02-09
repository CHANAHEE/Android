package com.example.tp01_numbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button[] btnArr;
    TextView num1,num2,num3,strike,ball,out,gameResult,round;
    int count, roundCount = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnArr = new Button[10];
        int startArr = R.id.btn0;

        btnArr[0] = findViewById(startArr);
        for(int i = 1; i < 10 ; i++){
            btnArr[i] = findViewById(++startArr);
        }

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);

        strike = findViewById(R.id.strike);
        ball = findViewById(R.id.ball);
        out = findViewById(R.id.out);

        gameResult = findViewById(R.id.gameResult);


        round = findViewById(R.id.round);

        roundThread rt = new roundThread(round,btnArr,count,roundCount,num1,num2,num3);




    }


}

class roundThread  {

    TextView round,num1,num2,num3;

    Button[] btnArr;
    int count,roundCount;
    public roundThread(TextView round, Button[] btnArr,int count, int roundCount,TextView num1,TextView num2,TextView num3){
        this.round = round;
        this.btnArr = btnArr;
        this.count = count;
        this.roundCount = roundCount;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }
    @Override
    public void run() {

        round.setVisibility(View.INVISIBLE);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        round.setText(roundCount + "");
        round.setVisibility(View.VISIBLE);

        count = 0;
        btnArr[0].setOnClickListener(listener0);
        btnArr[1].setOnClickListener(listener1);
        btnArr[2].setOnClickListener(listener2);
        btnArr[3].setOnClickListener(listener3);
        btnArr[4].setOnClickListener(listener4);
        btnArr[5].setOnClickListener(listener5);
        btnArr[6].setOnClickListener(listener6);
        btnArr[7].setOnClickListener(listener7);
        btnArr[8].setOnClickListener(listener8);
        btnArr[9].setOnClickListener(listener9);
    }

    private View.OnClickListener listener0 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            setNumber(btnArr[0]);
            allNumChecked(count);
        }
    };
    private View.OnClickListener listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            setNumber(btnArr[1]);
            allNumChecked(count);
        }
    };
    private View.OnClickListener listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            setNumber(btnArr[2]);
            allNumChecked(count);
        }
    };
    private View.OnClickListener listener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            setNumber(btnArr[3]);
            allNumChecked(count);
        }
    };
    private View.OnClickListener listener4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            setNumber(btnArr[4]);
            allNumChecked(count);
        }
    };
    private View.OnClickListener listener5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            setNumber(btnArr[5]);
            allNumChecked(count);
        }
    };
    private View.OnClickListener listener6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            setNumber(btnArr[6]);
            allNumChecked(count);
        }
    };
    private View.OnClickListener listener7 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            setNumber(btnArr[7]);
            allNumChecked(count);
        }
    };
    private View.OnClickListener listener8 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            setNumber(btnArr[8]);
            allNumChecked(count);
        }
    };
    private View.OnClickListener listener9 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            setNumber(btnArr[9]);
            allNumChecked(count);
        }
    };

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
    }
}

