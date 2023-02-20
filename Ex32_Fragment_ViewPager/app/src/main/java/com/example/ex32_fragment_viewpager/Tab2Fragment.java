package com.example.ex32_fragment_viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab2Fragment extends Fragment {

    RadioGroup rg;
    ImageView iv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rg = view.findViewById(R.id.rg_tab2);
        iv = view.findViewById(R.id.img_tab2);


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rb_Id = rg.getCheckedRadioButtonId();

                if(rb_Id == R.id.rb1) iv.setImageResource(R.drawable.bg_one05);
                else if(rb_Id == R.id.rb2) iv.setImageResource(R.drawable.bg_one06);
                else if(rb_Id == R.id.rb3) iv.setImageResource(R.drawable.bg_one07);
            }
        });

    }
}
