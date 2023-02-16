package com.example.ex23_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        // 3_ 두가지 방법으로 자바에서 할 수 있다. 하나는 앞선 리스트 예제에서해봤고
        // 여기서는 static 메소드 creatFromResource() 를 이용할 수 있다.
        adapter = ArrayAdapter.createFromResource(this,R.array.city,R.layout.spinner_item);

        // 4_ 드롭다운되는 아이템 뷰들과 스피너 뷰는 다른 뷰이다.
        // 그래서 드롭다운되는 아이템들의 뷰모양을 다르게 지정할 수도 있다.
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // 10_ 스피너의 드롭다운되는 뷰의 위치를 조정할 수 있다!
        spinner.setDropDownVerticalOffset(160);
        // 6_ 스피너의 아이템들 선택하자
        // 7_ 근데 아무것도 안눌러도 한번 토스트 메시지가 띄워진다. 즉, 셀렉티드는 시작하자마자 발동한다. 선택이 이미 되어있기 때문에! 클릭은 클릭을 해야 발동하는것!
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] city = getResources().getStringArray(R.array.city);
                Toast.makeText(MainActivity.this, city[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}