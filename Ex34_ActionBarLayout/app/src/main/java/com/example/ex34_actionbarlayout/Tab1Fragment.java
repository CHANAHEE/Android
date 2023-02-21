package com.example.ex34_actionbarlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class Tab1Fragment extends Fragment {

    Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn = view.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 탭 위치 변경 - 탭레이아웃에게 요청해야함. , 즉 메인액티비티에게 요청해야함.
                MainActivity mainActivity = (MainActivity) getActivity();
                TabLayout.Tab tab = mainActivity.tabLayout.getTabAt(2);
                mainActivity.tabLayout.selectTab(tab);
            }
        });
    }
}
