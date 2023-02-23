package com.example.ex43_activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etName,etAge;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(view -> {
            // 2_ SecondActivity 로 보낼 데이터가 필요하다!
            String name = etName.getText().toString();
            int age = Integer.parseInt(etAge.getText().toString());

            // 3_ 새로운 액티비티를 실행 시켜줄 택배기사님 생성
            Intent intent = new Intent(this, SecondActivity.class);
            // 4_ start 하기 전에 새로운 액티비티에 보낼 데이터를 택배기사님에게 넣기!! - putExtra()
            intent.putExtra("name",name);   // 5_ 식별자와 값을 전달하자
            intent.putExtra("age",age);


            startActivity(intent);// 6_ 정보를 담아서 보냈으니 이제 세컨트액티비티로 가자.
        });
    }
}