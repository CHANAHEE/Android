package com.mrhi2023.ex35navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ActionBarDrawerToggle drawerToggle;

    CircleImageView civ; //헤더뷰 안에 있는 둥근이미지뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout= findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.nav);

        //드로우어 토글 버튼객체 생성
        drawerToggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open, R.string.close);

        //액션바에 홈or업 버튼의 위치에 아이콘이 보이게끔
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 삼선아이콘(햄버거 아이콘) 모양으로 보이도록 토글버튼의 동기 맞추기
        drawerToggle.syncState();

        // 삼선아이콘과 화살표아이콘이 자동으로 변환되도록..
        drawerLayout.addDrawerListener(drawerToggle);

        // NavigationView의 메뉴항목들을 선택했을때 반응하기
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if( item.getItemId() == R.id.menu_gallery ){
                    Toast.makeText(MainActivity.this, "Gallery", Toast.LENGTH_SHORT).show();

                }else if( item.getItemId() == R.id.menu_send ){
                    Toast.makeText(MainActivity.this, "Send", Toast.LENGTH_SHORT).show();

                }else if( item.getItemId() == R.id.menu_aa ){
                    Toast.makeText(MainActivity.this, "aa", Toast.LENGTH_SHORT).show();

                }else if( item.getItemId() == R.id.menu_bb ){
                    Toast.makeText(MainActivity.this, "bb", Toast.LENGTH_SHORT).show();

                }

                // 드로우어 메뉴 닫기
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });


        // NavigationView 안에 있는 HeaderView 안에 있는
        // CircleImageView를 찾아오기
        View headerView= navigationView.getHeaderView(0);
        civ= headerView.findViewById(R.id.civ);
        civ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                civ.setImageResource(R.drawable.moana05);
            }
        });



    }
}