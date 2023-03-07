package com.example.ex69_photopickbyintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);

        findViewById(R.id.btn1).setOnClickListener(view -> clickBtn1());
        findViewById(R.id.btn2).setOnClickListener(view -> clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(view -> clickBtn3());
        findViewById(R.id.btn4).setOnClickListener(view -> clickBtn4());
        findViewById(R.id.btn5).setOnClickListener(view -> clickBtn5());
    }

    // 3_ 사진선택결과를 받아오는 계약을 체결하는 대행사 객체 등록. 우리는 사진을 보내는게 아니라 인텐트를 보내서 결과값을 받아와야 하기 때문!
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() != RESULT_CANCELED){
                // 4_ 선택된 사진의 정보를 가진 택배기사를 소환!
                Intent intent = result.getData();

                // 5_ 택배기사에게 사진정보 uri 데이터를 달라고 요청해야함
                Uri uri = intent.getData();
                tv.setText(uri.toString());

                // 6_ 이미지 로드 라이브러리 사용 [Glide, Picasso]
                Glide.with(MainActivity.this).load(uri).into(iv);
            }
        }
    });
    void clickBtn1(){
        // 1_ ACTION_PICK : 갤러리 및 포토 앱에서만 선택하는 친구
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*"); // 2_ 꼭 설정해주어야 하는 속성! 안쓰면 에러~ 한줄로 적을수도 있음.
        launcher.launch(intent);
    }

    void clickBtn2(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        launcher.launch(intent);
    }

    void clickBtn3(){
        // 7_ ACTION_OPEN_DOCUMENT : 모든 문서 선택 앱에서 선택하는 방법 - GET_CONTENT 의 대체용
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*");
        launcher.launch(intent);

    }

    void clickBtn4(){
        // 8_ 명시적 Intent 생성없이 사진선택 화면이 BottomSheet 형식으로 보여진다.
        // 9_ PickVisualMedia 는 위 작업을 해주는 대행사 클래스!
        pickMediaLauncher.launch(new PickVisualMediaRequest());
    }

    // 10_ 대행사 만들기
    ActivityResultLauncher<PickVisualMediaRequest> pickMediaLauncher = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            tv.setText(result.toString());
            Glide.with(MainActivity.this).load(result).into(iv);
        }
    });


    void clickBtn5(){
        // 11_ PickVisualMedia 와 같은 기능을 한다!
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        launcher.launch(intent);
    }
}