package com.example.ex72_cameraapp2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
    }


    // 2_ 촬영한 이미지가 저장될 파일의 URI[콘텐츠 경로 - DB resource 경로]
    Uri imgUri = null;

    void clickBtn(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 4_ 개발자가 저장되길 원하는 위치의 파일 경로 URI 를 만들어주는 기능을 호출하자.
        createImageUri();

        // 1_ 촬영한 이미지를 파일로 저장하도록 추가데이터를 설정하자! 추가데이터로 저장될 이미지의 Uri 값을 설정해주어야 촬영된 사진이 저장된다!!!!
        // 그리고 Uri 값을 설정하려면 Uri 값을 얻어와야 하는데, 사진이 저장될 실제 파일의 경로를 설정하고 그 경로를 기반으로 Uri 값으로 변환해주어야 한다!
        if(imgUri != null) intent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri); // 3_ Uri 가 null 이면 에러나니까, 조건문 달아주자.
        launcher.launch(intent);//6_ 일단 위 코드부터 실행하자. //28_ 이제 다시 실행시켜보자.

    }

    ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
        if(result.getResultCode() != RESULT_CANCELED){
            // 29_ 카메라앱이 촬영한 이미지를 EXTRA_OUTPUT 으로 지정한 imgUri 에 저장했을것임.
            Glide.with(this).load(imgUri).into(iv);
        }
    });

    // 5_ 이미지의 경로 Uri 를 생성하는 기능메소드를 정의하자.
    void createImageUri(){
        // 7_ 저장될 파일의 경로를 지정하자.
        //8_ 외부저장소에 저장하고자 함
        // 9_ 1. 외부저장소의 앱 전용영역
        // 10_ 2. 외부저장소의 미디어 영역

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); // 11_ 경로가 잘 설정되는지 확인해보자.
        tv.setText(path.toString()); // 12_ 주소가 맞게 잘 나온다. 사진이 저장될 경로가 잘 설정되었다!

        // 13_ 파일명
        // 이름이 동일하면 덮어쓰기가 되므로, 이름이 겹치지 않도록 파일의 이름을 정해야한다,
        // 그래서 보통 날짜를 이용하여 파일명을 지정한다.
        // String fileName = "EX72_IMG_20230307134730.jpg"; 14_ 이렇게 만들면 고정적이므로, 다시만들자
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "EX72_IMG_"+sdf.format(new Date())+".jpg";

        // 15_ 경로와 파일명.확장자를 가지고 파일객체를 하나 만들자
        File file = new File(path,fileName);
        tv.setText(file.toString());

        // 16_ 자 그러면 파일의 실제 경로는 구했다. 그럼 이 경로를 Uri 로 바꾸어주어야 함.

        // 17_ 카메라앱은 저장될 이미지의 실제 경로가 아니라, DB 주소에 해당하는 콘텐츠 경로가 필요함. 즉 Uri 가 필요함.
        // 18_ 지금 Uri 는 DB 에 저장되었다. 그리고 그 DB 는 내 앱의 DB 이다. 그리고 다른 앱에서
        // 내 앱의 DB 에 접근하려면 Contents Provider 가 필요하다!!

        // 19_ 그 중에서 파일에 대한 경로 제공 Provider 는 이미 클래스로 설계되어있음.
        // FileProvider
        imgUri = FileProvider.getUriForFile(this,"sam",file);

        // 27_ 여기까지 잘 했다면 imgUri 를 문자열로 찍어보자.
        tv.setText(imgUri.toString());
    }
}