package com.example.ex17_contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"버튼이 눌렸습니다",Toast.LENGTH_SHORT).show();
            }
        });

        // 1_ 액티비티 에게 btn 객체를 ContextMenu 로 등록해주어야 한다.
        registerForContextMenu(btn);
    }

    // 2_ ContextMenu 로 등록된 버튼 객체를 길게 누르면 컨텍스트 메뉴를 만드는 메소드가 자동으로 발동함.


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // 첫번째 파라미터 메뉴판떼기, 두번째 파라미터 등록된 뷰. 여기서는 버튼 객체.
        // 3_ menu 폴더 안에 context.xml 파일을 읽어서 메뉴 아이템 객체로 생성(부풀리다 : inflate) -> context.xml 파일을 만들자!
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_save){
            Toast.makeText(this,"save 버튼이 눌렸습니다.",Toast.LENGTH_SHORT).show();
        } else if(id == R.id.menu_delete){
            Toast.makeText(this,"delete 버튼이 눌렸습니다.",Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}