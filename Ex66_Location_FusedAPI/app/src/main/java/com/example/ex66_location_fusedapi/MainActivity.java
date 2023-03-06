package com.example.ex66_location_fusedapi;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    // 1_ Fused API : Google 지도앱에 사용되고 있는 위치정보 제공자 최적화 라이브러리

    // 2_ Google 기능 라이브러리를 추가해주어야 함 : play-services-location
    FusedLocationProviderClient providerClient;
    TextView tv;
    LocationCallback locationCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
    }

    void clickBtn(){
        // 3_ 위치정보는 동적 퍼미션이 필수!
        int checkPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if(checkPermission == PackageManager.PERMISSION_DENIED){
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            return;
        }

        // 4_ 위치정보 최적화 객체 소환하기
        providerClient = LocationServices.getFusedLocationProviderClient(this);

        // 6_ 위치정보의 최적화를 위한 기준이 필요하다. 와이파이일때만 해~ 혹은 정확한 위치정보가 필요해! 배터리 최적화로 필요해! 등등의 기준을 정하자.
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // 7_ 정확도 우선
        locationRequest.setInterval(5000); // 8_ 갱신주기

        // 5_ 위치 정보 갱신
        providerClient.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper());
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Location location = locationResult.getLastLocation();

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                tv.setText(latitude +", " + longitude);
            }
        };
        // 9_ Location Manager와 다른점은 분기문이 없다는점. 왜? 네트워크냐 gps 냐를 자동으로 찾아주기 때문에.

    }

    // 10_ 액티비티가 화면에서 안보이기 시작할 때 자동으로 발동하는 콜백메소드


    @Override
    protected void onPause() {
        super.onPause();

        if(providerClient != null){
            providerClient.removeLocationUpdates(locationCallback);
        }
    }

    ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            result -> {
                if(result) Toast.makeText(MainActivity.this, "퍼미션 허용됨", Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this, "퍼미션 거부됨", Toast.LENGTH_SHORT).show();
    });
}