package com.example.ex86_retrofitimageupload;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;
import com.example.ex86_retrofitimageupload.databinding.ActivityMainBinding;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    // 4_ 업로드할 파일의 주소를 저장하는 문자열 변수하나를 만들자.
    String imgPath = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSelect.setOnClickListener(view -> clickSelect());
        binding.btnUpload.setOnClickListener(view -> clickUpload());
    }

    void clickSelect(){
        // 1_ 이미지 선택할 수 있는 액티비티를 실행하자.
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        launcher.launch(intent);
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
        if(result.getResultCode() == RESULT_CANCELED) return;

        // 2_ 선택한 사진의 콘텐츠 주소 URI 정보 얻기
        Uri uri = result.getData().getData();
        Glide.with(this).load(uri).into(binding.iv);

        // 3_ 애석하게도 Retrofit 을 사용하여 서버에 파일을 전송하려면
        // 파일의 Uri, 즉 콘텐츠 주소가 아니라 파일의 주소가 필요하다. 다이얼로그로 주소확인해보자
        // new AlertDialog.Builder(this).setMessage(uri.toString()).create().show();

        // 5_ uri --> 파일 주소로 변환
        imgPath = getFilePathFromUri(uri);
        // new AlertDialog.Builder(this).setMessage(imgPath).create().show();
    });
    String getFilePathFromUri(Uri uri){
        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this,uri,projection,null,null,null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    void clickUpload(){
        // 6_ Retrofit 라이브러리를 이용하여 이미지 업로드
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://tjdrjs0803.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit = builder.build();

        // 8_ Service 인터페이스 객체를 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        // 9_ 보낼 파일을 택배상자로 포장
        File file = new File(imgPath);
        RequestBody body = RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("img",file.getName(),body);

        Call<String> call = retrofitService.uploadImage(part);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                new AlertDialog.Builder(MainActivity.this).setMessage(s).create().show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



    }
}