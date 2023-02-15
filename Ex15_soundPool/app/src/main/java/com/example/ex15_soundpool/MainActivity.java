package com.example.ex15_soundpool;

import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn01, btn02, btn03, btn04;
    SoundPool sp;
    SoundPool.Builder builder;
    int sdStart, sdAgain, sdGoodJob, sdMusic; // 6_ 음원 ID 저장 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1_ 사운드 풀 객체를 만들어서 음원들을 등록하자
        // 2_ 사운드 풀을 만들어주는 건축가 객체를 활용하자
        builder = new SoundPool.Builder();
        builder.setMaxStreams(10); // 3_ 한번에 플레이가 가능한 음원의 수 ! - 우선순위가 낮은 음원은 들리지 않는다. 나중에 틀었냐 는 중요하지 않음.
        sp = builder.build();

        // 4_ 음원을 등록하자 --> 사운드풀에 음원을 등록하면 음원의 고유 ID 를 발급받을 수 있다.
        sdStart = sp.load(this,R.raw.s_sijak,0); // 5_ 안드로이드에서는 등록할 떄는 우선순위를 0으로 지정하고, 플레이할 때 우선순위를 따로 정해주는걸 권장한다.
        sdAgain = sp.load(this,R.raw.s_again,0);
        sdGoodJob = sp.load(this,R.raw.s_goodjob,0);

        sdMusic = sp.load(this,R.raw.kalimba,0);

        btn01 = findViewById(R.id.btn01);
        btn02 = findViewById(R.id.btn02);
        btn03 = findViewById(R.id.btn03);
        btn04 = findViewById(R.id.btn04);

        btn01.setOnClickListener(listener );
        btn02.setOnClickListener(listener );
        btn03.setOnClickListener(listener );
        btn04.setOnClickListener(listener );
    }
View.OnClickListener listener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(view.getId() == btn01.getId()){
            sp.play(sdStart,0.9f,0.9f,3, 0 ,1.0f);
        }else if(view.getId() == btn02.getId()){
            sp.play(sdAgain,0.8f,0.8f,2, 0 ,1.0f);
        }else if(view.getId() == btn03.getId()){
            sp.play(sdGoodJob,0.7f,0.7f,1, 0 ,1.0f);
        } else {
            sp.play(sdMusic, 0.7f,0.7f,4,0,1.0f);
        }
    }
};
}