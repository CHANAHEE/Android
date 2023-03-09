package com.example.ex78_viewbinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ex78_viewbinding.databinding.FragmentMyBinding;

public class MyFragment extends Fragment {

    FragmentMyBinding binding;


    // 11_ 이 프래그먼트가 보여줄 뷰를 리턴해주는 기능메소드 재정의
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMyBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    // 13_ 버튼 클릭 이벤트 설정해보자!
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btn.setOnClickListener(v->binding.tv.setText("Good Day"));
    }

}
