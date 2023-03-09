package com.example.ex76_exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class FullScreenActivity extends AppCompatActivity {

    StyledPlayerView pv;
    ExoPlayer exoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        pv = findViewById(R.id.pv);
        exoPlayer = new ExoPlayer.Builder(this).build();
        pv.setPlayer(exoPlayer);

        // 11_ 플레이 시킬 동영상의 uri 가 필요하다. 그래야 뭐 틀게 있지.
        // 13_ 그래서 인텐트를 통해 전달받은 uri 를 가져오자.
        Intent intent = getIntent();
        Uri vedioUri = intent.getData();
        long currentPos = intent.getLongExtra("currentPos",0l);
        MediaItem mediaItem = MediaItem.fromUri(vedioUri);
        exoPlayer.setMediaItem(mediaItem,currentPos);
        exoPlayer.prepare();
        exoPlayer.play();
    }
}