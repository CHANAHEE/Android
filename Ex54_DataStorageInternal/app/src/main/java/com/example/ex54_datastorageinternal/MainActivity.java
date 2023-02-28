package com.example.ex54_datastorageinternal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);

        findViewById(R.id.btn_save).setOnClickListener(view -> clickSave());
        findViewById(R.id.btn_load).setOnClickListener(view -> clickLoad());
    }

    public void clickSave(){
        // 2_ 저장할 데이터를 가져오자
        String data = et.getText().toString();
        et.setText("");

        // 3_ 내부 저장소 (Internal Storage) 의 전용공간에 Data.txt 라는 파일에
        // 위 문자열 데이터를 저장합시다.

        // 4_ 파일 쪽으로 데이터를 내보내는(저장) 스트림 열기
        // 5_ 다행스럽게도 액티비티 클래스 안에 이미 내부 저장소의 파일을 여는 기능메소드가 존재함.
        try {
            FileOutputStream fos = openFileOutput("Data.txt",MODE_APPEND);
            // 6_ fos 는 바이트 단위로 데이터를 저장재주어야 하기 때문에, 불편하다
            // 7_ 그래서 문자 스트림으로 변환 하자. 여기서 한 단계 더 나아가서 곧바로 보조 문자 스트림을 사용하면 더 편하다.
            PrintWriter writer = new PrintWriter(fos);

            writer.println(data);
            writer.flush();
            writer.close();

            tv.setText("Saved");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void clickLoad(){

        // 8_ 내부저장소의 Data.txt 라는 파일에서 데이터 읽어오기
        try {
            FileInputStream fis = openFileInput("Data.txt");
            // 9_ 바이트 스트림 -> 문자스트림
            InputStreamReader isr = new InputStreamReader(fis);
            // 10_ 문자열 읽기 , 한줄 단위로 문자열 읽기가능
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();

            while(true){
                String line = reader.readLine(); // 11_ 라인을 읽어올 때도 예외처리가 필요함!
                // 12_ 그리고 readLine() 은 줄바꿈 문자를 제외하고 읽어온다!!

                if(line == null) break;

                buffer.append(line+"\n");
            }

            tv.setText(buffer.toString());

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "해당 파일을 찾을 수 없습니다", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}