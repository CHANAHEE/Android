package com.example.ex62_backgroundtaskbyservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


// 2_ 서비스는 안드로이드의 4대 주요 구성요소임. 반드시 메니페스트 파일에 등록해야 한다.

public class MyService extends Service {

    MediaPlayer mp; // 8_ Thread 를 상속받은 클래스 MediaPlayer

    //3_ startService() 로 서비스가 시작되면 자동으로 실행되는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        // 11_ 오레오 버전 부터 "Foreground Service" 라는 개념이 도입되었다.
        // 개발자들의 서비스 컴포넌트 사용의 남용으로, 사용자에게 배터리가 빨리 닳는 이슈가 있었다.
        // 그래서 안드로이드에서는 사용자들에게 백그라운드 서비스가 실행중임을 알려줄 필요성이 생겼고, 그것을 위해 Notification 의 개념을 활용한다.
        // Foreground Service : 사용자에게 서비스가 실행중임을 인식하도록 반드시 알림(Notification) 을 보이도록 강제하는 서비스 개념.

        // 19_ startForegroundService() 로 실행된 서비스 객체는 반드시 startForeground() 라는 메소드를 호출해야만 함.

        // 18_ Foreground Service 는 알림 객체를 만들어야 한다. 즉, 알림 객체를 만들고, Foreground Service 에게 실행하라고 요청해주어야 함.
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        NotificationCompat.Builder builder = null;

        if(Build.VERSION.SDK_INT > 26){
            NotificationChannelCompat channel = new NotificationChannelCompat.Builder("ch01",NotificationManager.IMPORTANCE_HIGH).setName("Ex62 알림채널").build();
            manager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this,"ch01");
        }else {
            builder = new NotificationCompat.Builder(this,"ch01");
        }
        builder.setSmallIcon(R.drawable.ic_noti);
        builder.setContentTitle("Ex62 Music Service");
        builder.setContentText("Music Service 가 실행중입니다");

        // 20_ 음악 재생/ 정지 버튼을 가진 MainActivity 를 알림창이 클릭되었을 때 실행되도록 하자.
        Intent i = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100,i,PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);

        // 21_ 이제 알림 객체를 생성하자.
        Notification notification = builder.build();
        startForeground(1,notification);

        // 9_ 그리고 서비스는 Context 의 능력을 가지고 있음. 또한 서비스는 Main Thread 와 또 다른 Main Thread 를 가지고 있음. 알아두기.
        if(mp == null) {
            mp = MediaPlayer.create(this,R.raw.kalimba);
            mp.setVolume(0.7f,0.7f);
            mp.setLooping(true);
        }
        mp.start();

        // 6_ 메모리 문제로 프로세스가 강제로 서비스를 kill 시켜버리는 경우가 있음. (ex 다운로드)
        // 그리고 메모리 문제가 해결되면 자동으로 다시 서비스를 실행시키도록 설정하는게 리턴값임.
        return START_STICKY;// 7_ 여러 설정값들이 있다. 여기에서는 START_STICKY 로 다시 살려주자.
    }
    // 5_ stopService() 로 서비스를 종료시키면 자동으로 실행되는 메소드


    @Override
    public void onDestroy() {
        if(mp != null){
            mp.stop();
            mp.release(); // 10_ 온전하게 미디어 파일들을 지우려면 release() 가 필요하다. 단순히 null 로 초기화한다고 지워지지 않는다. 뭔가 가비지 컬렉터가 제대로 못지우나봄.
            mp = null;
        }
        super.onDestroy();
    }

    // 4_ bindService() 로 서비스가 시작되면 자동으로 실행되는 메소드
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
