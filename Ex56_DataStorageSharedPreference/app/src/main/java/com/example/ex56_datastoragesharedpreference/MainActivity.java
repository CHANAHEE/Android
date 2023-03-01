package com.example.ex56_datastoragesharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etName,etAge;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);

        tv = findViewById(R.id.tv);

        findViewById(R.id.btn_save).setOnClickListener(view -> clickSave());
        findViewById(R.id.btn_load).setOnClickListener(view -> clickLoad());
    }

    public void clickSave(){
        // 1_ 저장할 데이터
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());

        // 2_ SharedPreferences 로 저장하자. 사실 내부적으로는 파일로 저장이 된다.
        // "Data.xml" 파일에 데이터를 저장하기 위해 객체를 얻어오자
        // 3_ getPreferences(), getSharedPreferences() 의 차이는 하나의 액티비티에 관한 설정이냐, 앱 전체에 관한 설정이냐 이다! 보통 화면설정보다는 앱 설정이 많기 떄문에 세어드를 쓰자.
        SharedPreferences pref = getSharedPreferences("Data",MODE_PRIVATE); // 모드는 정해진거라고 생각하자. 어펜드할꺼면 파일입출력 써라~ 그리고 xml 로 저장해야만 하기 떄문에 확장자는 안적는다.


        // 4_ 저장작업을 시작하자
        SharedPreferences.Editor editor = pref.edit();// 5_ 에디터 객체가 리턴됨.
        editor.putString("name",name); // 6_ 식별자도 같이 put 해주자
        editor.putInt("age",age);

        editor.apply();
        tv.setText("저장 완료");
    }
    public void clickLoad(){
        SharedPreferences pref = getSharedPreferences("Data",MODE_PRIVATE);
        String name = pref.getString("name","none");
        int age = pref.getInt("age",0);
        tv.setText(name + " " + age);
    }
}