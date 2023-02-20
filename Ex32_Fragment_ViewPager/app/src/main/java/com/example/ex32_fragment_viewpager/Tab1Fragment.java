package com.example.ex32_fragment_viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab1Fragment extends Fragment {

    ImageView iv;
    Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1,container,false);
        return view;
    }

    // 위에 있는 onCreateView() 가 실행된 후 만들어진 뷰에 여러가지 설정작업을 위해 실행되는 콜백 메서드가 있다!! 위 메서드에서 설정작업은 하지말자.

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { // 첫번째 파라미터 view 는 onCreateView()의 리턴값
        super.onViewCreated(view, savedInstanceState);
        iv = view.findViewById(R.id.img_tab1);
        btn = view.findViewById(R.id.btn_tab1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setImageResource(R.drawable.bg_one02);
            }
        });
    }
}
