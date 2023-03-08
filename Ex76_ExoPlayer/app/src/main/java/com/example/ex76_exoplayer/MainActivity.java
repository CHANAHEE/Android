package com.example.ex76_exoplayer;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class MainActivity extends AppCompatActivity {

    PlayerView playerView;
    StyledPlayerView pv;
    ExoPlayer exoPlayer2;
    ExoPlayer exoPlayer;
    MediaItem mediaItem;
    // 1_ 동영상 주소
    Uri videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        playerView = findViewById(R.id.playview);
        exoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);

// 4_ 이거는 CD 하나짜리~ 영상 하나니까 여러개를 해보자!!


        // 2_ 영상을 CD 로 굽듯이.
        mediaItem = MediaItem.fromUri(videoUri);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        // 3_ 리스너 필요없이, 자동으로 로딩완료까지 기다렸다가 재생한다.
        exoPlayer.play();

//        Uri firstUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4");
//        Uri secondUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4");
//
//        MediaItem item1 = MediaItem.fromUri(firstUri);
//        MediaItem item2 = MediaItem.fromUri(secondUri);
//
//        exoPlayer.addMediaItem(item1);
//        exoPlayer.addMediaItem(item2);

        exoPlayer.prepare();
        exoPlayer.play();
        exoPlayer.setRepeatMode(ExoPlayer.REPEAT_MODE_ALL);

        // 5_ 콘트롤 박스 모양 별도 레이아웃으로 지정
        // 6_ 레이아웃 폴더에 만들자. 근데 이미 지정된 레이아웃파일의 이름이 있다. 만들어보자.

        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());



        // 17_ 개선된 콘트랄바 모양을 가진 스타일드 플레이어뷰
        pv = findViewById(R.id.pv);
        exoPlayer2 = new ExoPlayer.Builder(this).build();
        pv.setPlayer(exoPlayer2);

        exoPlayer2.setMediaItem(mediaItem);
        exoPlayer2.prepare();
        exoPlayer2.play();











    }

    Intent intent;
    void clickBtn(){
        // 9_ 전체화면 모드로 변경 - 별도의 전체화면용 액티비티를 실행
        intent = new Intent(this, FullScreenActivity.class);

        // 12_ 현재 재생중인 동영상의 uri 를 전달해야한다.
        intent.setData(videoUri);

        // 14_ 현재까지 재생된 위치정보도 같이 추가로 전달해야겠다
        long currentPos = exoPlayer.getCurrentPosition();
        intent.putExtra("currentPos",currentPos);
        launcher.launch(intent);
    }
    Uri resultUri = null;
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
        resultUri = intent.getData();
    });
    // 15_ 화면이 안보이기 시작할 때 동영상 일시정지

    @Override
    protected void onPause() {
        super.onPause();
        exoPlayer.pause();
    }

    // 16_ 액티비티가 완전히 종료될 때, 플레이어 완전 종료시키기.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoPlayer.stop();
        exoPlayer.release();
        exoPlayer = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //exoPlayer.setMediaItem(mediaItem,);
    }
}