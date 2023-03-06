package com.example.ex68_kakaomap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.kakao.util.maps.helper.Utility;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity {

    // 2_ 카카오 개발자 사이트의 가이드문서를 통해, 따라하면 라이브러리를 추가하면된다.
    // 다만, 라이브러리를 추가하고 적용하는 과정은 가이드문서에 없기 때문에 주의할것.
    // 3_ 이제 퍼미션을 추가하자.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 9_ 키 해시 값 얻어오기
        String keyHash = Utility.getKeyHash(this);
        Log.i("keyHash",keyHash);

        // 11_ 맵뷰 만들기
        MapView mapView = new MapView(this);
        ViewGroup viewGroup = findViewById(R.id.container_mapview);
        viewGroup.addView(mapView);

        // 12_ 여기까지만 해도 지도는 보여야 함. 실디바이스가 아니라 다운됨.

        // 13_ 가이드문서에 지도를 다루는 코드들이 모두 소개되어 있다!
        // 14_ 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);

        // 15_ 마커 표시하기 [POI]
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("마커");
        marker.setMapPoint( MapPoint.mapPointWithGeoCoord(37.12596,127.09402));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(marker);

        // 16_ 구글맵 라이브러리만 추가하는 모습만 확인해보자 귀찮으니 이 프로젝트에서 넣어보자.
        com.google.android.gms.maps.MapView mapView1 = new com.google.android.gms.maps.MapView(this);
    }
}