package com.example.ex18_popupmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1_ 마법의 양탄자 팝업메뉴 만들자!
                // PopupMenu 객체를 생성하자
                // PopupMenu popupMenu = new PopupMenu(MainActivity.this,btn);
                // 5_ 팝업메뉴를 버튼 말고 다른 뷰에 붙이기 
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,tv);

                // 2_ PopupMenu 가 보여줄 메뉴를 설계하자! 어디에? menu 폴더안에 popup.xml 으로 별도로!
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.popup,popupMenu.getMenu()); // 왜 여기서만 getMenu 인지 모르겠네!! 옵션이랑 컨텐스트랑 차이점 알아보자!

                popupMenu.show();

                // 3_ 팝업 메뉴의 항목이 '클릭'되었을 때 반응하기
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if(id == R.id.menu_info) Toast.makeText(MainActivity.this,"인포메이션 버튼이 클릭되었습니다.",Toast.LENGTH_SHORT).show();
                        else if(id == R.id.menu_delete) Toast.makeText(MainActivity.this, "삭제버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
                        else if(id == R.id.menu_modify) Toast.makeText(MainActivity.this, "변경버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        });
    }
}