package com.example.tp06_matchpic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageButton startBtn,howToBtn;
    ImageButton[] imgAnimalArr = new ImageButton[5];
    ImageView imgBoard,result;
    ArrayList<Integer> animal_img_list;
    ArrayList<Integer> board_img_list;
    SoundPool.Builder builder;
    SoundPool sp;
    int sp_again, sp_goodjob, sp_select, sp_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.game_start);
        howToBtn = findViewById(R.id.how_to_play);

        imgAnimalArr[0] = findViewById(R.id.image_monkey);
        imgAnimalArr[1] = findViewById(R.id.image_pig);
        imgAnimalArr[2] = findViewById(R.id.image_lion);
        imgAnimalArr[3] = findViewById(R.id.image_frog);
        imgAnimalArr[4] = findViewById(R.id.image_ele);

        imgBoard = findViewById(R.id.board);
        result = findViewById(R.id.result);

        animal_img_list = new ArrayList<>();
        animal_img_list.add(R.drawable.a_monkey);
        animal_img_list.add(R.drawable.a_pig);
        animal_img_list.add(R.drawable.a_lion);
        animal_img_list.add(R.drawable.a_frog);
        animal_img_list.add(R.drawable.a_ele);

        board_img_list = new ArrayList<>();
        board_img_list.add(R.drawable.b_monkey);
        board_img_list.add(R.drawable.b_pig);
        board_img_list.add(R.drawable.b_lion);
        board_img_list.add(R.drawable.b_frog);
        board_img_list.add(R.drawable.b_ele);

        for(ImageButton ib : imgAnimalArr) ib.setEnabled(false);

        startBtn.setOnClickListener(listener_start);
        howToBtn.setOnClickListener(listener_how_to_play);

        builder = new SoundPool.Builder();
        builder.setMaxStreams(10);
        sp = builder.build();

        sp_again = sp.load(MainActivity.this,R.raw.s_again,0);
        sp_goodjob = sp.load(MainActivity.this,R.raw.s_goodjob,0);
        sp_select = sp.load(MainActivity.this,R.raw.s_select,0);
        sp_start = sp.load(MainActivity.this,R.raw.s_sijak,0);

        for(int i=0;i<imgAnimalArr.length;i++){
            imgAnimalArr[i].setOnClickListener(listener);
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String btnTag = (String) v.getTag();
            String boardTag = (String) imgBoard.getTag();
            sp.play(sp_select,1.0f,1.0f,3,0,1.0f);

            if(btnTag.equals(boardTag)){
                sp.play(sp_goodjob,1.0f,1.0f,3,0,1.0f);
                result.setImageResource(R.drawable.button_start);
                result.setVisibility(View.VISIBLE);
            }else{
                sp.play(sp_again,1.0f,1.0f,3,0,1.0f);
                result.setImageResource(R.drawable.button_howto);
                result.setVisibility(View.VISIBLE);
            }
        }
    };
    View.OnClickListener listener_start = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for(ImageButton ib : imgAnimalArr) ib.setEnabled(true);
            sp.play(sp_start,1.0f,1.0f,2,0,1.0f);
            result.setVisibility(View.INVISIBLE);
            Collections.shuffle(animal_img_list);
            imgBoard.setImageResource(board_img_list.get(0));
            imgBoard.setTag("monkey");
            int animal = 0;

            for(int i = 0; i<animal_img_list.size();i++){
                animal = animal_img_list.get(i);
                Drawable drawable = AppCompatResources.getDrawable(MainActivity.this,animal);
                imgAnimalArr[i].setBackground(drawable);
                if(animal == R.drawable.a_monkey) imgAnimalArr[i].setTag("monkey");
                else if(animal == R.drawable.a_pig) imgAnimalArr[i].setTag("pig");
                else if(animal == R.drawable.a_lion) imgAnimalArr[i].setTag("lion");
                else if(animal == R.drawable.a_frog) imgAnimalArr[i].setTag("frog");
                else if(animal == R.drawable.a_ele) imgAnimalArr[i].setTag("ele");

            }
        }
    };

    View.OnClickListener listener_how_to_play = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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
    };
}