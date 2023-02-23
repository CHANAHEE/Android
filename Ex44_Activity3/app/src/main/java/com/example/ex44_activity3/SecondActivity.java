package com.example.ex44_activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText etName,etAge;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener( view -> {
            // 4_ EditText 에 입력한 데이터들을 결과로 설정하자!
            String name = etName.getText().toString();
            int age = Integer.parseInt(etAge.getText().toString());

            // 5_ 다시 돌아갈 택배기사 Intent 객체에게 결과 데이터를 넣자
            Intent intent = getIntent();
            intent.putExtra("name",name);
            intent.putExtra("age",age);

            setResult(RESULT_OK, intent); // 6_ 완료버튼을 누르면 RESULT_OK 를 전달한다. 그리고 정보를 담은 intent 도 함께 전달한다.
            finish(); // 7_ 완료가 됐으니 액티비티를 종료해야지~
        });
    }
}