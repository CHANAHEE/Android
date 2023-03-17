package com.example.ex90_firebasechat;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ex90_firebasechat.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    // 1_ 프로필 이미지 Uri
    Uri profileUri;
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.civProfile.setOnClickListener(view -> clickImage());
        binding.btnStart.setOnClickListener(view -> clickBtn());

        // 디바이스에 저장되어 있는 로그인 정보가 있는지 확인하자
        // SharedPreferences 에 저장되어 있는 닉네임, 프로필 이미지가 있다면 읽어오자.
        loadData();

        if(G.nickName != null){
            binding.etNickname.setText(G.nickName);
            Glide.with(this).load(G.profileUrl).into(binding.civProfile);

            isFirst = false;
        }
    }

    void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("profile",MODE_PRIVATE);
        G.nickName = sharedPreferences.getString("nickName",null);
        G.profileUrl = sharedPreferences.getString("profileUrl",null);
    }
    void clickImage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        launcher.launch(intent);
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
        if(result.getResultCode() == RESULT_CANCELED) return;

        profileUri = result.getData().getData();
        Glide.with(this).load(profileUri).into(binding.civProfile);


    });

    void clickBtn(){
        // 2_ 채팅화면으로 가기전에 프로필 이미지와 닉네임을 서버에 저장시켜주어야 한다.
        if(isFirst) saveData();
        else startActivity(new Intent(this, ChattingActivity.class));

    }

    void saveData(){
        // 3_ 이미지를 선택하지 않으면 채팅이 불가하도록 하자
        if(profileUri == null) {
            Toast.makeText(this, "프로필 사진을 설정하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        // 4_ EditText 에 있는 닉네임 가져오기
        // 5_ 전역변수 역할의 G 클래스를 만들어서 static 으로 처리!
        G.nickName = binding.etNickname.getText().toString();

        // 6_ 이미지 업로드가 오래걸리기 때문에 FirebaseStorage 부터 먼저 업로드하자.
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // 7_ 참조위치 명이 중복되지 않도록 날짜를 이용하자.
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "IMG_" + format.format(new Date());
        StorageReference imgRef = storage.getReference("profileImage/"+fileName);

        // 8_ 이미지 업로드
        imgRef.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // 9_ 업로드가 성공되었으니...
                // 업로드된 파일의 다운로드 URL 주소가 필요하다. 가져오자.
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        G.profileUrl = uri.toString();
                        Toast.makeText(MainActivity.this, G.profileUrl.toString(), Toast.LENGTH_SHORT).show();

                        // 1. 서버의 Firestore DB 에 닉네임과 이미지 url 저장

                        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                        // 'profiles' 라는 이름의 컬렉션 참조객체 소환
                        CollectionReference profileRef = firestore.collection("profiles");
                        // 닉네임을 Document 이름으로 정하고 필드 '값' 으로 이미지경로 url 저장
                        Map<String,Object> profile = new HashMap<>();
                        profile.put("profileUrl",G.profileUrl);

                        // 도규먼트 만들자.
                        profileRef.document(G.nickName).set(profile);

                        // 2. 앱을 처음 실행할 때만 닉네임과 사진을 입력하도록 하기 위해
                        // 이 디바이스에 영구적으로 데이터를 저장하자.[SharedPreference]
                        SharedPreferences sharedPreferences = getSharedPreferences("profile",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("nickName",G.nickName);
                        editor.putString("profileUrl",G.profileUrl);

                        editor.commit();

                        // 10_ 저장이 완료 되었으면, 채팅화면으로 이동하면 됨.
                        Intent intent = new Intent(MainActivity.this, ChattingActivity.class);
                        startActivity(intent);

                        finish();
                    }
                });
            }
        });
    }
}