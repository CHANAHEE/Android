package com.example.ex71_cameraapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
    }

    void clickBtn(){
        // 1_ 카메라 앱을 실행하자.
        // 카메라 앱을 실행한다는것. 새로운 화면을 띄운다는것 -> 인텐트를 만들자
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        launcher.launch(intent);
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result ->{
                if(result.getResultCode() != RESULT_CANCELED){
                    // 2_ 사진촬영 파일의 uri 를 얻어오자.
                    Intent intent = result.getData();
                    Uri uri = intent.getData();

                    // 3_ 프로그램에서 실행한 카메라앱에서 촬영한 사진은 디바이스에 파일로 저장하지 않도록 정책이 바뀌었다.
                    // 4_ 그래서 촬영한 사진정보를 Bitmap 객체로 만들어서 Extra 데이터로 전달해준다.
                    Bundle bundle = intent.getExtras();// 5_ Extra 데이터들을 Bundle 에 담았다.
                    Bitmap bm = (Bitmap) bundle.get("data"); // 6_ 번들에 담은 데이터들, 즉 촬영된 사진 정보를 Bitmap 객체에 담는다. 키값은 data 이다.
                    iv.setImageBitmap(bm);// 7_ 가져온 비트맵 객체를 이용하여 이미지뷰에 뿌려준다.
                    // 8_ 근데 사진을 보면 약간 흐리멍텅하다. 사실, 촬영한 사진정보는 섬네일 정보로 온다. 그래서 작은 이미지라면 문제없다.
                    // 9_ 마구잡이로 사진을 스토리지에 저장되지 못하게 하기 위해 만들어진 기술이다.
                    // 10_ 그러면 파일로 저장을 하게 만들고싶다면 어떻게 해야할까? -> 인텐트로 카메라 앱을 실행할 때 추가 데이터를 설정해주어야 한다! 다음예제로..


                }
            }
    );}