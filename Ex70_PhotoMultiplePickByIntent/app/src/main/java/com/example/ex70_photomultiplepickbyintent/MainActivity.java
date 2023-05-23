package com.example.ex70_photomultiplepickbyintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;

//import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ArrayList<Uri> images = new ArrayList<>();
    MyAdapter adapter;
    ViewPager2 pager;
   //SpringDotsIndicator dotsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        pager = findViewById(R.id.pager);
        adapter = new MyAdapter(this,images);
        pager.setAdapter(adapter);

        // 5_ 도트 인디케이터도 사용해보자~ 쉽네
//        dotsIndicator = findViewById(R.id.dots_indicator);
//        dotsIndicator.attachTo(pager);

        findViewById(R.id.btn1).setOnClickListener(view -> clickBtn1());
        findViewById(R.id.btn2).setOnClickListener(view -> clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(view -> clickBtn3());


    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK){
                    // 2_ 사진 uri 들을 가져온 택배기사를 소환하자.

                    result.getData().getClipData().getItemCount();
                    Intent intent = result.getData();
                    assert intent != null;
                    ClipData clipData = intent.getClipData(); // 3_ 여러개면 getData() 가 아니라 getClipData(); !!
                    Log.i("Hdddd",result.getData().toString());
                    int size = clipData.getItemCount();

                    for(int i =0;i<size;i++){
                        images.add(clipData.getItemAt(i).getUri());
                    }
                    tv.setText(size+"");
                    adapter.notifyDataSetChanged();
                }
            }
    );
    void clickBtn1(){
        // 1_ putExtra 로 사진을 여러개 선택하겠다는 정보를 전달하자. 인텐트 클래스가 그 키값을 가지고 있음
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE).setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        launcher.launch(intent);

        // 4_ 이제 첫번째 버튼을 누르면, 사진을 가져와서, 뷰페이져에 뿌려주자. 그러기 위해 어댑터랑, 뷰페이저의 시안을 만들자. page.xml과 MyAdapter 를 설계.
    }


    void clickBtn2(){
        multiplePickLauncher.launch(new PickVisualMediaRequest());
    }


    ActivityResultLauncher<PickVisualMediaRequest> multiplePickLauncher = registerForActivityResult(new ActivityResultContracts.PickMultipleVisualMedia(), new ActivityResultCallback<List<Uri>>() {
        @Override
        public void onActivityResult(List<Uri> result) {
            for( Uri uri : result){
                images.add(uri);
            }
            adapter.notifyDataSetChanged();
        }
    });
    void clickBtn3(){
        // 6_ PickVisual 을 대신 해주는 인텐트가 있다~
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES).putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX,10);// 7_ ALLOW_MULTIPLE 아님~ 최대개수를 정해주면된다!
        launcher.launch(intent);
    }
}