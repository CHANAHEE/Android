package com.example.ex87_retrofitmarketapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ex87_retrofitmarketapp.databinding.ActivityEditBinding;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditActivity extends AppCompatActivity {

    ActivityEditBinding binding;

    // 1_ 업로드 할 이미지의 파일 경로 변수
    String imgPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("글작성");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnSelect.setOnClickListener(view -> clickSelect());
        binding.btnComplete.setOnClickListener(view -> clickComplete());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    void clickSelect(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        launcher.launch(intent);
    }
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == RESULT_CANCELED) return;

        Uri uri = result.getData().getData();
        Glide.with(this).load(uri).into(binding.iv);

        // 2_ Retrofit 을 이용하여 서버에 이미지를 보낼 때는 Uri 주소가 아닌 파일의 실제 주소가 필요함.
        imgPath = getFilePathFromUri(uri);
        // new AlertDialog.Builder(this).setMessage(imgPath).create().show();
    });

    String getFilePathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return result;
    }



    void clickComplete(){
        // 3_ 사용자가 입력한 데이터를 서버에 전송하자.
        // 4_ 전송할 데이터 [ name , title , message , price , imgPath ]
        String name = binding.etName.getText().toString();
        String title = binding.etTitle.getText().toString();
        String message = binding.etMsg.getText().toString();
        String price = binding.etPrice.getText().toString();

        // 10_ String 데이터들을 HashMap 데이터로 만들어 주자
        Map<String,String> dataPart = new HashMap<>();
        dataPart.put("name",name);
        dataPart.put("title",title);
        dataPart.put("message",message);
        dataPart.put("price",price);

        // 11_ 이미지 파일 택배박스를 포장하자.
        MultipartBody.Part filePart = null;
        if(imgPath != null){
            File file = new File(imgPath);
            RequestBody body = RequestBody.create(MediaType.parse("image/*"),file);
            filePart = MultipartBody.Part.createFormData("img",file.getName(),body);
        }


        // 5_ Retrofit 작업 시작!
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<String> call = retrofitService.postDataToServer(dataPart,filePart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                Toast.makeText(EditActivity.this, s, Toast.LENGTH_SHORT).show();
                // 13_ php 작업도 다 끝났다! 그래서 게시글을 올렸는데, 올려도 에디트 창이 안없어진다. 없애주자.
                // 14_ 일단 데이터를 입력해서 DB 에 저장시키는 작업까지는 끝났다. 이제 다른 사용자가 내 글을 볼 수 있도록 하기 위해
                // 불러오는 php 파일을 먼저 만들어주자.
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(EditActivity.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // 12_ 여기까지 만들었으면 이제 insertDB.php 파일 만들러가자!
    }
}