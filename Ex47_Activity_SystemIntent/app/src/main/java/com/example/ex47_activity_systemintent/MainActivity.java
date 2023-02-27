package com.example.ex47_activity_systemintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        findViewById(R.id.btn0).setOnClickListener(view -> {
            Intent intent2 = new Intent(this, SecondActivity.class);
            startActivity(intent2);
            finish();
//            intent2 = getIntent();
//            String bb = intent2.getStringExtra("name");
//            tv.setText(bb);
        });


findViewById(R.id.btn1).setOnClickListener(view -> {
    // 1_ 다이얼화면(전화 앱)
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_DIAL);
    // 2_ 미리 전화번호까지 전달하려면 다음과 같은 작업이 필요하다.
    intent.setData(Uri.parse("tel:01012345678"));
    startActivity(intent);
});

findViewById(R.id.btn2).setOnClickListener(view -> {
    Intent intent = new Intent(Intent.ACTION_SENDTO); // 3_생성자로 액션 설정이 가능하다!!
    intent.setData(Uri.parse("smsto:01012345678 , smsto:01022224444"));
    // 4_ 문자 내용을 프로그래밍 가능하다.
    intent.putExtra("sms_body","안녕하세요");
    startActivity(intent);
});

findViewById(R.id.btn3).setOnClickListener(view -> {
    // 5_ 웹페이지 보는 화면 - 크롬브라우저 앱
    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.naver.com"));// 액션과 데이터를 한번에!
    startActivity(intent);
});

findViewById(R.id.btn4).setOnClickListener(view -> {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivity(intent);
});
    }
}