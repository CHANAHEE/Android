package com.example.ex19_searchview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SearchView searchView; // 6_ 참조변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1_ 서치뷰는 옵션메뉴에서 만들어지기 때문에 메뉴폴더부터 만들자.


    }

    // 2_ onCreate() 메소드가 실행된 후에 옵션메뉴를 만드는 작업을 하는 콜백메서드가 자동발동함.
    // 액션바는 메인 xml 이 생성되기 이전에 만들어진다. 액티비티에 의해 만들어지기 때문.

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 3_ 원래 아래 코드
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.option_search,menu);
//        // 4_ 간편하게 참조변수 없이 만들자.
        getMenuInflater().inflate(R.menu.option_search,menu);

        // 6_ 서치뷰의 id 값을 가져오려면 여기서 find 해주어야한다!! 메인액티비티가 서치뷰를 가지고 있진 않으니..
        // 7_ 서치뷰를 한번 찾아보자. 어디있는지. getItem() 은 위치 기준으로 찾는건데, 이건 헷갈린다. 그래서 findItem 을 사용!
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        // 8_ 서치뷰는 메뉴아이템 안에있는 뷰. 그래서 메뉴아이템한테 찾아달라고 해야함.
        searchView = (SearchView) menuItem.getActionView();

        // 9_ 서치뷰에 적용하는 설정
        searchView.setQueryHint("이름을 입력하세요");

        // 10_ 서치뷰에 글씨변화에 반응하는 리스너를 설정하자!
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 12_ 소프트 키보드의 돋보기 버튼을 선택했을 때 실행되는 콜백메서드(검색어 입력 완료)
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "검색어 : "+query, Toast.LENGTH_SHORT).show();
                return false;
            }

            // 11_ 글씨가 변경될 때 마다 실행되는 콜백메서드
            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, "검색어 : "+newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}