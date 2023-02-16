package com.example.ex16_optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 1_ onCreate() 가 실행된 후 자동으로 Option Menu 를 만드는 작업을 하는 콜백메소드가 자동으로 발동함.
    // 내용이 없어 따로 적어주지 않았을 뿐 자동으로 실행되는 메소드임.

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 2_ Menu 객체에게 MenuItem 객체를 추가하기
        // 2_1 Java 언어로 추가해보기 : 아이콘이나 id 같은 식별자를 지정할 때 번거롭다! 특히 메뉴가 많아질때..!!
        // menu.add("aa"); // 그래서 이방법은 많이 사용하지 않는다.


        // 2_2 XML 언어로 menu 로 '설계'하고 객체로 만들어서 사용해보기
        // this.getSystemService(Context.) 이렇게 해야하나, 자주 사용되는 스머프들은 아래처럼 사용한다.

        // 3_ menu 폴더안에 option.xml 문서를 읽어와서 Menu 객체로 만들어주는(부풀려주는 : inflate) 객체 얻어오기
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option,menu);

        return super.onCreateOptionsMenu(menu);
    }

    // 7_ Option Menu 의 메뉴항목(MenuItem) 이 선택되었을 때 자동으로 발동하는 콜백 메서드

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {// 선택된 메뉴항목이 파라미터로 전달됨.

        if(item.getItemId() == R.id.menu_search){
            Toast.makeText(this, "search 항목이 선택되었습니다.",Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.menu_add){
            Toast.makeText(this,"add 항목이 선택 되었습니다.",Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.menu_help){
            Toast.makeText(this,"help 항목이 선택 되었습니다.",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}