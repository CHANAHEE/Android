package com.example.ex44_activity3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        btn.setOnClickListener(view -> {
            // 0_ 세컨액티비티 화면설계하고, 액티비티 화면에 버튼이랑 텍스트뷰 설계
            // 1_ 결과를 받기 위해 SecondActivity 를 실행하자
            Intent intent = new Intent(this, SecondActivity.class);
            launcher.launch(intent); // 그냥 start .. 하면 갔다가 안돌아온다. 그래서 대행사를 통해 보내야 다시 돌아온다.
        });

    }

    // 2_ 인텐트의 작업을 대신 해줄 수있는 하청업체 객체를 생성하자.
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

            // 3_ 혹시 실행시켰던 액티비티에서 [뒤로가기 버튼]으로 취소했을 수도 있어서
            if(result.getResultCode() == RESULT_OK){
                // 8_ 세컨액티비티에서 setResult() 에서 설정해줬던게 RESULT_OK 냐? 이렇게 물어보는거임. 사실 아무런 숫자를 넣어도 상관없지만 이건 약속이니 지키자!
                // 9_ 어쨋든 완료가 잘 되었으니, 돌아온 인텐트 객체를 소환하자.
                Intent intent = result.getData(); // 10_ 우리가 받는건 인텐트객체이다! 그래서 메서드 이름이 좀 그렇지만 그냥 받아들이자.
                String name = intent.getStringExtra("name");
                int age = intent.getIntExtra("age",0);
                tv.setText(name + " " + age);

            }else if(result.getResultCode() == RESULT_CANCELED){
                Toast.makeText(MainActivity.this, "취소되었습니다", Toast.LENGTH_SHORT).show();
            }
        }
    });
}