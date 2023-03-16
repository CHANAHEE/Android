package com.example.ex88_firebasestorage;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;
import com.example.ex88_firebasestorage.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // Firebase : 안드로이드 앱을 개발할 때 서버단의 작업을 코딩한줄 없이 사용하는 플랫폼.
    // 이 프로젝트와 Firebase 플랫폼을 연동하는것이 첫번째! - Firebase 가이드 문서를 참고하자!

    // 1. 파이어 베이스 사이트에 로그인 하고, 개발자 콘솔에서 프로젝트를 만들자.
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLoad.setOnClickListener(view -> clickLoad());
        binding.btnSelect.setOnClickListener(view -> clickSelect());
        binding.btnUpload.setOnClickListener(view -> clickUpload());
    }

    void clickLoad(){
        // Firebase Storage 에 저장되어 있는 이미지 파일을 읽어오자.
        // Firebase Storage 관리 객체가 있음. 소환하자.
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();


        // 저장소의 최상위 위치를 참조하는 참조객체를 얻어오자.
        StorageReference rootRef = firebaseStorage.getReference();

        // 읽어오길 원하는 파일의 참조객체를 얻어오자.
        StorageReference imgRef = rootRef.child("bg_one10.jpg");
        if(imgRef != null){
            // 파일 참조 객체로 부터 이미지의 다운로드 URL 얻어오자.
            imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(MainActivity.this).load(uri).into(binding.iv);
                }
            });
        }
    }

    Uri imgUri = null;
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        imgUri = result.getData().getData();
        Glide.with(this).load(imgUri).into(binding.iv);
    });

    void clickSelect(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        launcher.launch(intent);
    }

    void clickUpload(){
        if(imgUri == null) return;

        // 파이어베이스 저장소 관리 객체 소환
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "IMG_"+simpleDateFormat.format(new Date())+".png";
        // 저장할 파일 위치에 대한 참조객체
        StorageReference imgRef = firebaseStorage.getReference("photo/"+fileName); // photo 폴더가 없으면 만들고 있으면 그냥 참조

        // 위 저장 경로 참조객체에게 실제 파일 업로드 시키기
        imgRef.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}