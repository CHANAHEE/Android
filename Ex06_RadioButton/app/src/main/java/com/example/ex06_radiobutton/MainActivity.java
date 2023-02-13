package com.example.ex06_radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    RadioGroup rg;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        rg = findViewById(R.id.rg);
        tv = findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 2_ 라디오버튼 중에 선택된 녀석의 글씨를 얻어와서 TextView 에 보여주기
                // 3_ 라디오 그룹에게 체크된 라디오 버튼의 id 얻어오기
                int checkedID = rg.getCheckedRadioButtonId();

                // 4_ 체크된 id 의 라디오버튼 객체를 참조하자.
                RadioButton rb = findViewById(checkedID);
                tv.setText(rb.getText().toString());
            }
        });

        // 5_ 라디오버튼의 체크 상태가 변경될 때 마다 반응하는 리스너는 버튼에 붙이는게 아니라, 버튼 그룹에 붙이는것
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // 두번째 파라미터 i : 선택된 라디오버튼의 ID 값.
                RadioButton rb = findViewById(i);
                tv.setText(rb.getText().toString());
            }
        });
    }
}