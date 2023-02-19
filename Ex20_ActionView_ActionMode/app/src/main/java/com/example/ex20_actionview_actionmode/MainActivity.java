package com.example.ex20_actionview_actionmode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText actionViewEt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1010_ 액션모드 메뉴 : 새로운 액션바를 만들어서 옵션메뉴를 붙이는 방식

                // 액션 모드 시작
                startActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        // 3_ 액션 모드가 처음 실행될 때 메뉴항목들을 만들기 위해 자동실행되는 메소드 . 처음만들어질때 한번만 실행.
                        actionMode.getMenuInflater().inflate(R.menu.option_actionmode,menu);

                        // 8_ 액션 모드에 의해 만들어진 제목줄(ActionBar) 의 제목 글씨 표시
                        actionMode.setTitle("ActionMode");
                        actionMode.setSubtitle("hello");

                        // 9_ 액션모드의 배경색은 테마에서 지정

                        return true; // 리턴값이 true 여야만 액션모드가 발동함.
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        // 4_ 액션모드가 시작될 때 마다 실행되는 콜백메서드 얘는 매번 호출됨. 할때 마다마다.
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        // 5_ 메뉴항목이 클릭되었을 때 실행되는 콜백메서드.
                        if(menuItem.getItemId() == R.id.menu_map)
                            Toast.makeText(MainActivity.this, "맵 버튼 클릭", Toast.LENGTH_SHORT).show();
                        else if(menuItem.getItemId() == R.id.menu_share)
                            Toast.makeText(MainActivity.this, "공유 버튼 클릭", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {
                        // 6_ 액션모드가 없어질 때 실행되는 콜백메서드

                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_actionview,menu);
        // ActionView 로 지정한 커스텀뷰 안에 있는 EditText 를 찾아오자.
        MenuItem item = menu.findItem(R.id.menu_action);
        LinearLayout layout = (LinearLayout) item.getActionView();
        actionViewEt = layout.findViewById(R.id.actionview_et);

        // EditText 의 소프트 키보드 중에서 작성완료 버튼 (서치모양 버튼) 을 클릭하는것에 반응하는 리스너 객체 생성 및 설정
        actionViewEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                // 키보드에서 어떤 키를 눌렀는지를 가지고 있는 변수가 있다. 그게 두번째 파라미터 i 이다! Editor.info 는 키보드의 모든 키의 정보를 가지고 있음!
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    String message = actionViewEt.getText().toString();
                    Toast.makeText(MainActivity.this, "검색어 : " + message, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}