package com.example.tp06_matchpic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageButton startBtn,howToBtn,imgMonkey,imgPig,imgLion,imgFrog,imgEle;
    ImageView imgBoard;
    ArrayList<Integer> aList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.game_start);
        howToBtn = findViewById(R.id.how_to_play);
        imgMonkey = findViewById(R.id.image_monkey);
        imgPig = findViewById(R.id.image_pig);
        imgLion = findViewById(R.id.image_lion);
        imgFrog = findViewById(R.id.image_frog);
        imgEle = findViewById(R.id.image_ele);

        imgBoard = findViewById(R.id.board);

        aList = new ArrayList<>();
        aList.add(R.drawable.b_monkey);
        aList.add(R.drawable.b_pig);
        aList.add(R.drawable.b_lion);
        aList.add(R.drawable.b_frog);
        aList.add(R.drawable.b_ele);
        Collections.shuffle(aList);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBoard.setImageResource(aList.get(0));
                imgEle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });

        howToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("게임설명");
                builder.setMessage("게임시작 버튼을 누르면 게임이 시작됩니다.\n게임이 시작되면 하단부에 있는 단어와 동물이 일치하도록 동물을 터치하세요!");

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
}