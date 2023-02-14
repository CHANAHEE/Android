package com.example.tp05_widgetex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton favoriteBtn, chatBtn, sendBtn, downloadBtn, moreBtn;
    ImageView mainIv,iv;
    AlertDialog dialog;

    int[] images;

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new int[]{R.drawable.newyork, R.drawable.paris, R.drawable.sydney};

        favoriteBtn = findViewById(R.id.favoriteBtn);
        chatBtn = findViewById(R.id.chatBtn);
        sendBtn = findViewById(R.id.sendBtn);
        downloadBtn = findViewById(R.id.downloadBtn);
        moreBtn = findViewById(R.id.moreBtn);

        mainIv = findViewById(R.id.mainImage);

        favoriteBtn.setOnClickListener(listener);
        chatBtn.setOnClickListener(listener);
        sendBtn.setOnClickListener(listener);
        downloadBtn.setOnClickListener(listener);
        moreBtn.setOnClickListener(listener);



        mainIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(R.layout.dialog);
                dialog = builder.create();
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                iv = dialog.findViewById(R.id.dialogImage);
                iv.setImageResource(images[flag]);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flag++;
                        if(flag == 3) flag = 0;
                        iv.setImageResource(images[flag]);


                    }
                });
            }
        });


    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == favoriteBtn.getId()){
                Toast.makeText(MainActivity.this, "좋아요를 눌렀습니다.",Toast.LENGTH_SHORT).show();
            }else if(view.getId() == chatBtn.getId()){
                Toast.makeText(MainActivity.this, "댓글을 눌렀습니다.",Toast.LENGTH_SHORT).show();
            }else if(view.getId() == sendBtn.getId()){
                Toast.makeText(MainActivity.this, "공유하기를 눌렀습니다.",Toast.LENGTH_SHORT).show();
            }else if(view.getId() == downloadBtn.getId()){
                Toast.makeText(MainActivity.this, "다운로드를 눌렀습니다.",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "더보기 버튼을 눌렀습니다.",Toast.LENGTH_SHORT).show();
            }

        }
    };
}