package com.example.ex62_backgroundtaskbyservice;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(view -> {
            // 1_ 백그라운드 작업을 Service 컴포넌트를 이용해서 실행해봅시다.
            Intent intent = new Intent(this,MyService.class);


            // 12_ 오레오 부터 Foreground Service 가 도입되었다. - 퍼미션도 필요하다.
            if(Build.VERSION.SDK_INT > 26){
                startForegroundService(intent);
            } else {
                startService(intent);
            }


        });

        findViewById(R.id.btn_stop).setOnClickListener(view -> {
            Intent intent = new Intent(this,MyService.class);
            stopService(intent);
        });

        // 15_ 알림에 대한 퍼미션 체크 및 요청
        int checkResult = checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS);
        if(checkResult == PackageManager.PERMISSION_DENIED){
            // 16_ 퍼미션 요청결과를 받아주는 대행사 객체를 활용해보자.
            permissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        }
    }

    // 17_ 대행사 객체를 생성 및 등록
    ActivityResultLauncher<String> permissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if(result == true){
                Toast.makeText(MainActivity.this, "알림 허용", Toast.LENGTH_SHORT).show();
                
            }else{
                Toast.makeText(MainActivity.this, "알림 불가. 서비스도 불가", Toast.LENGTH_SHORT).show();
            }
        }
    });

    @Override
    public void onBackPressed() {
        finish();
    }
}