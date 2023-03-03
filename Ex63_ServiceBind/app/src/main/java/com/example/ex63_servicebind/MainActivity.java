package com.example.ex63_servicebind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyMusicService musicService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_play).setOnClickListener(view -> clickPlay());
        findViewById(R.id.btn_pause).setOnClickListener(view -> clickPause());
        findViewById(R.id.btn_stop).setOnClickListener(view -> clickStop());
    }

    // 3_ 액티비티가 화면에 보여질 때, 자동으로 발동하는 메소드
    @Override
    protected void onResume() {
        super.onResume();

        if(musicService == null) {
            // 4_ MyMusicService 를 실행하고 연결까지 해보자.
            Intent intent = new Intent(this, MyMusicService.class);
            startService(intent); // 5_ Service 객체가 없으면 create 하고 onStartCommand() 를 호출함. 있으면 그냥 onStartCommand() 만 호출함.

            // 6_ Service 객체와 Bind 하여 터널 뚤고, MyMusicService 주솟값 받아오기
            bindService(intent,connection,0); // 8_ flag 에 AUTO_CREATE 를 하면 자동으로 서비스 객체를 생성해준다.
            // 9_ 근데, 그러면 AUTO_CREATE 를 하면되지 뭐하러 startService 를 하는거지?
            // AUTO_CREATE 로 서비스 객체를 만들면, MainActivity 가 죽으면, 서비스 객체도 동작을 멈추게 된다.
            // 단, startService 로 서비스 객체를 만들면 MainActivity 와 독립적으로 실행되므로 죽지 않게된다. 이게 차이점!
        }
    }

    // 7_ MyMusicService 와 연결하는 터널 객체를 만들자. 바인드 객체가 주소를 가지고 올 터널임.
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // 10_ 서비스와 bind 했다면, 이 메소드가 실행될거임. 확인해보기 위해 여기에 토스트 하나 띄우자.
            Toast.makeText(MainActivity.this, "bind !", Toast.LENGTH_SHORT).show();

            // 14_ 두번째 파라미터 : iBinder - 터널을 통해 넘어온 서비스 객체
            musicService = ((MyMusicService.MyBinder) iBinder).getServiceObject();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    void clickPlay(){
        if(musicService != null) musicService.playMusic();
    }

    void clickPause(){
        if(musicService != null) musicService.pauseMusic();
    }

    void clickStop(){
        if(musicService != null) {
            musicService.stopMusic();
            unbindService(connection);// 스탑을 누르면 서비스와 연결이 종료되버림.
            musicService = null;
        }

        Intent intent = new Intent(this, MyMusicService.class);
        stopService(intent);

        finish(); // 액티비티 종료
    }
}