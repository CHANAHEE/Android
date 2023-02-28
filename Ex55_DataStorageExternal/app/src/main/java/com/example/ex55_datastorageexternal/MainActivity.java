package com.example.ex55_datastorageexternal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        // 1_ 외부메모리(SD card) 가 있는지 확인부터 하자.
        String state = Environment.getExternalStorageState(); // 2_ String 값으로 반환된다! 단순히 true false 값이 아님

        // 3_ 외부메모리의 상태가 연결(mounted) 되어 있지 않은가를 확인하자
        if(!state.equals(Environment.MEDIA_MOUNTED)){
            Snackbar.make(tv,"SD card is not mounted",Snackbar.LENGTH_SHORT).show();
            return;
        }

        // 4_ 저장할 데이터를 가져오자!
        String data = et.getText().toString();
        et.setText("");

        // 5_ "Data.txt" 라는 파일에 데이터를 저장하자
        // 내부저장소와 다르게 액티비티에 스트림을 곧바로 열어주는 기능이 없다.
        // 그래서 직접 스트림을 열기위해서 File 객체를 생성하자.

        // 6_ 파일이 저장될 경로 : 앱에게 할당된 개별 영역 을 얻어오자
        File[] dirs = getExternalFilesDirs("MyDir"); // 7_ 디렉토리스~ 를 사용하는것을 권장함. 아까 말했듯, 내장메모리, sd카드, usb 여러개가 있으므로.. 파라미터에는 하위폴더명 작성!
        String s = "";
        for(File f : dirs){
            s += f.getPath() + "\n";
        }
        tv.setText(s);

        // 8_ 여러 경로 중 첫번째가 보통 SD card 의 경로이다!
        // 파일명과 경로를 결합하여 File 객체를 생성하자 -> 이 파일 객체를 만드는 이유가 우리가 스트림을 만들때 필요하기 때문!
        // 아까 내부저장소에서는 액티비티가 이 파일객체를 만들어주는 셈이었음!
        File file = new File(dirs[0],"Data.txt");

        // 9_ file 과 연결하는 무지개 로드 만들기
        // 다행히도 곧바로 문자스트림으로 만들수 있음.
        try {
            FileWriter fw = new FileWriter(file,true);// 10_ 덧붙이기 모드로 해주자. 만약 없으면 덮어쓰기 모드가 됨.
            PrintWriter writer = new PrintWriter(fw);
            writer.println(data);
            writer.flush();
            writer.close();

            tv.setText("Saved");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickLoad(){
        // 11_ SD card 가 읽을 수 있는 상태인지 확인하자
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            // 12_ 여기까지 왔으면 이제 외부저장소에서 읽어올 수 있는 상태가 되었다.
            File[] dirs = getExternalFilesDirs("MyDir");
            File file = new File(dirs[0],"Data.txt");

            try {
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);

                StringBuffer buffer = new StringBuffer();
                while(true){

                    String line = reader.readLine();
                    if(line == null) break;
                    buffer.append(line + "\n");
                }

                tv.setText(buffer.toString());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}