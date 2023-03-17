package com.example.ex89_firebase_firestoredb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ex89_firebase_firestoredb.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSave.setOnClickListener(view->clickSave());
        binding.btnLoad.setOnClickListener(view->clickLoad());


    }

    void clickSave(){
        String name = binding.etName.getText().toString();
        int age = Integer.parseInt(binding.etAge.getText().toString());
        String address = binding.etAddress.getText().toString();

        // 0_ FireStore DB 에 저장하자.[Map Collection 단위로 저장]
        // 1_ 객체 생성
        FirebaseFirestore firestore =  FirebaseFirestore.getInstance();

        // 2_ Person 이라는 이름의 컬렉션을 참조하는 참조 객체를 소환하자.
        CollectionReference personRef = firestore.collection("person"); // 없으면 만들고, 있으면 참조

        // 3_ Field 값들을 Map 으로 준비
        Map<String,Object> person = new HashMap<>();
        person.put("name",name);
        person.put("age",age);
        person.put("address",address);
        
        // 4_ personRef 참조 컬렉션에 값들 넣기
        // 5_ document 파라미터로 아무것도 전달하지 않으면 랜덤한 문자열이 지정됨.
        personRef.document().set(person).addOnSuccessListener(unused -> {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        });

        //personRef.document().set(person).
    }
    void clickLoad(){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference personRef = firestore.collection("person");

        personRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                // 6_ Collection 안에 여러개의 Document 가 있다.
                StringBuffer buffer = new StringBuffer();
                for(QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                    Map<String,Object> person = snapshot.getData();
                    String name = (String)person.get("name");
                    int age = Integer.parseInt(person.get("age").toString());
                    String address = (String) person.get("address");

                    buffer.append(name + " : " + age + " : " + address + "\n");
                }

                binding.tv.setText(buffer.toString());

            }
        });

        // ** 별외. 특정 필드값에 해당하는 데이터를 검색하고 싶다면?
//        personRef.get(); // 이건 도큐먼트의 모든 데이터를 가져오는것.
//
//        // name 속성의 값이 sam 인것.
//        personRef.whereEqualTo("name","sam").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//            }
//        });
    }
}