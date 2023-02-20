package com.example.ex30_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    // 7_ 이 프래그먼트가 화면에 보여줄 뷰를 만들어서 리턴해주면 액티비티가 보여줌
    // 이를 위해 자동으로 호출되는 콜백메소드 onCreateView !!!

    Button btn, btn2;
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 8_ fragment_my.xml 에 설계한 뷰를 객체로 생성하여 리턴
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        // 10_ 참조변수에 버튼, 텍스트뷰 id 값 넣어주고 버튼리스너 만들기
        tv = view.findViewById(R.id.tv);
        btn = view.findViewById(R.id.btn);
        btn2 = view.findViewById(R.id.btn2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("Hello Fragment");
            }
        });

        //. 근데 Fragment 도 보여줄 수 있게되었다. 사실, Fragment 가 View 를 리턴시켜서 액티비티가 보여줄 수 있는것임.
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 12_ MainActivity 의 TextView 를 제어해보자!
                // 13_ 먼저, Fragment.java 안에서 Activity 를 소환하자
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.tv.setText("good~~");
            }
        });
        return view;
    }
}
