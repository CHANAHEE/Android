package com.example.ex13_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et01,et02,et03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et01 = findViewById(R.id.et01);
        et02 = findViewById(R.id.et02);
        et03 = findViewById(R.id.et03);

        // EditText 의 글씨가 변경될 때 마다 감시하는 리스너 객체 생성 및 추가
        et01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et01.requestFocus()) et01.setText("555");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 글자수가 3글자 인지 확인
                if(charSequence.length() >= 3) et02.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}