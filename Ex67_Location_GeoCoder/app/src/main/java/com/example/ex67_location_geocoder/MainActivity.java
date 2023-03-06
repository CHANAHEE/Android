package com.example.ex67_location_geocoder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText et,etLat,etLong;

    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        etLat = findViewById(R.id.et_lat);
        etLong= findViewById(R.id.et_long);

        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
        findViewById(R.id.btn2).setOnClickListener(view -> clickBtn2());
        findViewById(R.id.btn_map).setOnClickListener(view -> clickBtn3());
    }

    void clickBtn(){
        // 1_ 주소 --> 좌표 [지오코딩] : 지오코딩 작업은 Google 지도 서버를 이용함. 인터넷을 사용해야한다. 즉 퍼미션이 필요하다!
        String addr = et.getText().toString(); // 2_ 검색주소
        // 3_ 지오코딩을 해주는 객체를 생성하자
        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
        // 4_ 주소에 해당하는 좌표가 여러개일 수 있음. 그래서 좌표결과를 List 로 반환한다.
        try {
            List<Address> addresses = geocoder.getFromLocationName(addr,3);
            StringBuffer buffer = new StringBuffer();
            for(Address address : addresses){
                buffer.append(address.getLatitude() + ", " + address.getLongitude() + "\n");
            }

            // 13_ 지도에 보여줄 좌표값을 멤버변수에 넣자.
            latitude = addresses.get(0).getLatitude();
            longitude = addresses.get(0).getLongitude();

            new AlertDialog.Builder(this).setMessage(buffer.toString()).create().show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void clickBtn2(){
        // 5_ 좌표 --> 주소 [역지오코딩]
        double latitude = Double.parseDouble(etLat.getText().toString());
        double longitude = Double.parseDouble(etLong.getText().toString());

        // 6_ 지오코더 객체를 만들자
        Geocoder geocoder = new Geocoder(this,Locale.KOREA);

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude,3);
            StringBuffer buffer = new StringBuffer();
            for(Address address : addresses){
                buffer.append(address.getCountryName() +"\n"); // 7_ 나라이름 받아오기
                buffer.append(address.getCountryCode() +"\n"); // 8_ 나라코드 받아오기
                buffer.append(address.getPostalCode() +"\n"); // 9_ 우편번호 받아오기
                buffer.append(address.getAddressLine(0) +"\n"); // 10_ 주소1 받아오기 : 도로명 건물번호
                buffer.append(address.getAddressLine(1) +"\n"); // 11_ 주소2 받아오기 : 상세주소 1 -- 없으면 null
                buffer.append(address.getAddressLine(2) +"\n"); // 12_ 주소3 받아오기 : 상세주소 2 -- 없으면 null
                buffer.append("========================\n");
            }

            new AlertDialog.Builder(this).setMessage(buffer.toString()).create().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void clickBtn3(){
        // 14_ 지도앱을 실행시키는 인텐트객체를 만들자
        Intent intent = new Intent(Intent.ACTION_VIEW);

        // 15_ 지도의 좌표 uri를 만들자
        Uri uri = Uri.parse("geo:"+latitude+","+longitude);
        intent.setData(uri);
        startActivity(intent);
    }
}