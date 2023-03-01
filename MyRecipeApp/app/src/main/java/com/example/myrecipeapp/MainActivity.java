package com.example.myrecipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Fragment bookmarkFragment,homeFragment;
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        bookmarkFragment = new BookmarkFragment();

        /*
        *
        *  앱 실행시 첫 화면을 홈화면으로 지정하기
        *
        * */
        getSupportFragmentManager().beginTransaction().add(R.id.container_fragment,homeFragment).commit();

        bnv = findViewById(R.id.bnv);

        bnv.setOnItemSelectedListener( item -> selectedItem(item));
    }

    /*
    *
    *  BottomNavigationView 아이템 선택 리스너 / 각 프래그먼트를 화면에 띄워준다.
    *
    * */
    public boolean selectedItem(MenuItem item){

        if(item.getItemId() == R.id.recipes_tab1) getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,homeFragment).commit();
        else if(item.getItemId() == R.id.bookmark_tab2) getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,bookmarkFragment).commit();
        return true;
    }
}