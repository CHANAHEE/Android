package com.example.ex41_bottomnavigationview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    Fragment[] fragments = new Fragment[3];
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();
        // 프래그먼트를 만들기만했을뿐 뷰에 붙이지는 않았다! 그러니까 메모리랑은 상관 X

        // 시작할 때 보여줄 Fragment 붙이기
        // 프래그먼트의 제어는 별도의 관리자를 통해 수행함.
        getSupportFragmentManager().beginTransaction().add(R.id.container_fragment,fragments[0]).commit();

        bnv = findViewById(R.id.bnv);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.bnv_tab1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,fragments[0]).commit();
                }
                else if(item.getItemId() == R.id.bnv_tab2) {
                    getSupportFragmentManager().beginTransaction().remove(fragments[1]);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,fragments[1]);
                }
                else if(item.getItemId() == R.id.bnv_tab3) {
                    getSupportFragmentManager().beginTransaction().remove(fragments[2]);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,fragments[2]).commit();
                }

                // return true; 로 하지 않으면 탭이 변경되는 UI 가 반영되지 않음.
                return true;
            }
        });
    }
}