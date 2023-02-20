package com.example.ex32_fragment_viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyAdapter extends FragmentStateAdapter {
    // 2_ 프래그먼트의 배열을 만들자.
    Fragment[] fragments = new Fragment[3];

    public MyAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
