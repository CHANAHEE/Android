package com.example.ex65_location;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    TextView tv, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1_ 위치정보 관리자 객체 소환
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        // 2_ 디바이스에서 위치정보를 제공하는 장치 여러개들을 위치정보 제공자(Location Provider)라고함.

        // 3_ 디바이스에서 제공하는 Provider 의 종류들을 먼저 확인해보기
        List<String> providers = locationManager.getAllProviders();
        StringBuffer buffer = new StringBuffer();
        for (String provider : providers) {
            buffer.append(provider + ", ");
        }

        tv.setText(buffer.toString());


        // 4_ 버튼을 누르면 내 위치 얻어와보자.
        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
        findViewById(R.id.btn2).setOnClickListener(view -> clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(view -> clickBtn3());

        // 8_ 위치정보제공에 대한 동적 퍼미션을 위한 다이얼로그 작업, FINE 에 대한 동적퍼미션만 설정하면 COARSE 는 자동으로 설정됨.
        int checkPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if (checkPermission == PackageManager.PERMISSION_DENIED) {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            return;// 10_ 퍼미션이 허용되지 않았다면, 대행사를 통해 퍼미션 허용을 요청하자.
        }
    }

    // 9_ 퍼미션 요청 및 결과를 받아주는 대행사 만들기
    ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            // 11_ result 는 퍼미션이 허용되었다면, true, 아니면 false
            if (result) Toast.makeText(MainActivity.this, "퍼미션 허용", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainActivity.this, "퍼미션 불가", Toast.LENGTH_SHORT).show();
        }
    });

    void clickBtn() {
        Location location = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        } // 12_ onCreate() 에 작성된 퍼미션 체크를 여기에서도 해주어야 함. if 안에는, FINE 과 COARSE 퍼미션이 둘다 허용되지 않았다면, clickBtn() 을 return 하는 조건임.

        if (locationManager.isProviderEnabled("fused")) {
            location = locationManager.getLastKnownLocation("fused");
        }else if( locationManager.isProviderEnabled("gps") ){
            location = locationManager.getLastKnownLocation("gps");
        }else if( locationManager.isProviderEnabled("network") ){
            location = locationManager.getLastKnownLocation("network");
        }// 5_ passive 는 다른앱에서 정보를 가져오는것이니 사실 의미없음..


        // 13_ 자 그러면 여기까지 코드가 왔다면, 어떠한 위치정보를 가져오긴 했을테니, 위치정도를 위도 경도로 얻어오자.

        if(location == null){
            tv2.setText("위치를 찾을수 없습니다.");
        } else {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            tv2.setText(latitude + " , " + longitude);
        }
    }

    // 14_ 버튼을 누르면 위치 정보 업데이트를 자동으로!
    void clickBtn2(){
        // 17_ 여기도 퍼미션이 허용되었는지 체크가 필요하므로, 퍼미션을 체크하고, 허용되지 않았으면 메소드를 종료시키는 아래 코드가 필요.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if(locationManager.isProviderEnabled("fused")){
            // 15_ 프로바이더 종류 / 몇초마다 갱신할건지 / 어느정도의 거리가 변경되면 갱신할건지 / 로케이션 리스너 달기
            locationManager.requestLocationUpdates("fused",5000, 2, listener );
        } else if(locationManager.isProviderEnabled("gps")){
            locationManager.requestLocationUpdates("gps",5000, 2, listener );
        } else if(locationManager.isProviderEnabled("network")){
            locationManager.requestLocationUpdates("network",5000, 2, listener );
        }
    }

    // 16_ if 문 3개에 모두 리스너를 달아줘야하니 하나로 만들어서 requestLocationUpdates 파라미터로 리스너 객체를 넣어주자.
    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            tv3.setText(latitude + ", " + longitude);
        }
    };

    void clickBtn3(){
        // 18_ 내 위치 갱신 종료
        locationManager.removeUpdates(listener);
    }
}