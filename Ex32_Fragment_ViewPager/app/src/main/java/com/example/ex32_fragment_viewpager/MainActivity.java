package com.example.ex32_fragment_viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

// 1_ 프래그먼트의 xml 3개를 만든다 -> 각각의 자바파일을 만든다. -> 프래그먼트를 띄워주는 어댑터를 만든다
public class MainActivity extends AppCompatActivity {

    ViewPager2 pager;
    MyAdapter adapter;
    TabLayout tabLayout;
    String[] tabTitle = new String[] {"TAB1","TAB2","TAB3"};
    TabLayoutMediator mediator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        adapter = new MyAdapter(this); // 메인액티비티가 프래그먼트를 가지고 있을 액티비티 이므로.
        pager.setAdapter(adapter);

        // 탭만들기
        tabLayout = findViewById(R.id.tab_layout);
        // TabLayout 과 viewPager 를 연동하기 - 중재자 객체(Mediator)
        mediator = new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                // pager 의 개수만큼 이 메소드가 실행됨 - 이곳에서 tab 글씨 같은것을 설정함.
                tab.setText(tabTitle[position]);
            }
        });// 마지막 파라미터는 탭에 글씨를 뭐쓸껀지 결정하는 객체를 전달해주어야 함.
        mediator.attach();
    }
}