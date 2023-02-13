package com.example.ex05_compoundbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // 2_ 액티비티가 보여줄 뷰의 참조변수는 멤버변수로...
    CheckBox cb01,cb02,cb03;
    Button btn;
    TextView tv;
    ToggleButton tb;
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 3_ xml 에서 만든 뷰들을 찾아와서 참조변수들에 대입해주기
        cb01 = findViewById(R.id.cb01);
        cb02 = findViewById(R.id.cb02);
        cb03 = findViewById(R.id.cb03);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);
        tb = findViewById(R.id.tb);
        sw = findViewById(R.id.sw);

        // 4_ 버튼을 클릭했을 때, 반응하기 위해서 버튼 클릭 이벤트를 듣는 리스너 객체를 생성 및 설정 하자.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 5_ 체크박스들이 체크되어 있는지 확인하여 체크된 녀석의 글씨를 텍스트뷰에 보여주자!
                // 6_ 근데 setText 파라미터로 getText() 를 넣어주면 추가가 안되고 갱신이 된다! set!!
                // 7_ 그래서 String 에 문자열을 더하는 방식으로 처리!
                String s = "";
                if(cb01.isChecked()) s += cb01.getText().toString() + "\n";
                if(cb02.isChecked()) s += cb02.getText().toString() + "\n";
                if(cb03.isChecked()) s += cb03.getText().toString() + "\n";
                tv.setText(s);
            }
        });

        // 9_ 체크박스의 체크상태가 변경될때마다 반응하는 리스너를 설정하기
        cb01.setOnCheckedChangeListener(checkedChangeListener);
        cb02.setOnCheckedChangeListener(checkedChangeListener);
        cb03.setOnCheckedChangeListener(checkedChangeListener);

        // 12_ 토글버튼 리스너 만들기
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 13_ 두번째 파라미터 b : boolean 값 - 체크된 상태값 true / false
                tv.setText("체크상태 : " + !b);
            }
        });
        
    }

    // 10_ 근데 리스너를 세놈이나 만들려니 뭔가 비효율적.. 그래서 체크상태 변경을 듣는 리스너를 멤버변수 위체이 설정해놓자.
    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            String s = "";
            if(compoundButton.isChecked()) s += compoundButton.getText().toString();
            tv.setText(s);
        }
    };
}