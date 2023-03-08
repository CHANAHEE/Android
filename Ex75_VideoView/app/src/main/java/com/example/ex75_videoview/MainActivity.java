package com.example.ex75_videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView vv;

    // 1_ 실제 비디오 파일은 용량이 크기때문에, 보통은 앱의 res 폴더에 직접 가지고있지 않는다.
    // 2_ 보통은 웹서버(인터넷 경로)에 동영상을 업로드하고 이를 불러와서 재생함.
    String videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("video15","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vv = findViewById(R.id.vv);

        // 6_ 근데 비디오를 재생, 일시정지등을 할 수 없다. 컨트롤바가 없기때문!
        // 7_ 그래서 비디오뷰에 컨트롤바를 붙이자.
        vv.setMediaController(new MediaController(this));


        vv.setVideoURI(Uri.parse(videoUrl));

        // 3_ setVideoURI 를 하면 별도의 스레드에서 네트워크 작업을 통해 동영상을 얻어온다.
        // 4_ 그래서 start() 를 바로 하면 바로 재생이 안될가능성이 높다.
        // 5_ 즉, 로딩이 완료되었을 때 재생하도록 해보자.
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                vv.start();

            }
        });

        findViewById(R.id.btn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }

    // 8_ 비디오뷰를 사용할 때는 거의 반드시 붙여줘야 하는 기능.
    // 9_ 근데 AVD 에서 사용해보니,
    @Override
    protected void onPause() {
        Log.i("video15","onPause");
        super.onPause();
        if(vv.isPlaying() && vv != null) vv.pause();
    }

    @Override
    protected void onResume() {
        Log.i("video15","onResume");
        super.onResume();
        if(!vv.isPlaying() && vv != null) vv.stopPlayback();
    }
}