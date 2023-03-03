package com.example.ex63_servicebind;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyMusicService extends Service {

    // 5_ 서비스 객체가 생성되면 자동으로 발동하는 콜백메소드
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    // 2_ startService() 로 실행하면 자동으로 발동하는 콜백 메서드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }


    // 1_ bindService() 를 실행하면 자동으로 발동하는 콜백 메서드
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return new MyBinder(); // 11_ 여기로 리턴된 값은 바인더 객체이며, 이 값은 MainActivity 의 onServiceConnected() 의 파라미터로 전달된다.
    }

    // 12_ 터널을 통해 MainActivity 로 넘어갈 Binder 클래스를 설계하자.
    class MyBinder extends Binder {
        // 13_ 여기에는 이 서비스 객체의 주솟값을 딸려보내야 한다. 주소값을 리턴해주는 기능을 하나 만들어놓자.
        public MyMusicService getServiceObject(){
            return MyMusicService.this;
        }
    }


    // 15_ 음악 재생 객체 및 기능 메소드를 구현해보자.
    MediaPlayer mp;

    public void playMusic(){
        if(mp == null) {
            mp = MediaPlayer.create(this,R.raw.kalimba);
            mp.setVolume(0.7f,0.7f);
            mp.setLooping(true);
        }
        if(!mp.isPlaying()) mp.start();
    }

    public void pauseMusic(){
        if(mp != null && mp.isPlaying()) mp.pause();
    }

    public void stopMusic(){
        if(mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
