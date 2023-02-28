package com.example.ex41_bottomnavigationview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tab3Fragment extends Fragment {

    ArrayList<Tab3RecyclerViewItem> items = new ArrayList<>();

    ArrayList<Tab3RecyclerViewItem> items2 = new ArrayList<>();

    ArrayList<Tab3RecyclerViewItem> items3 = new ArrayList<>();
    Tab3RecyclerAdapter adapter,adapter2,adapter3;
    RecyclerView recyclerView,recyclerView2,recyclerView3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items.add(new Tab3RecyclerViewItem("BLACK", R.drawable.ic_action_home));


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview1);
        adapter = new Tab3RecyclerAdapter(items,getActivity());
        recyclerView.setAdapter(adapter);

//        recyclerView2 = view.findViewById(R.id.recyclerview2);
//        adapter2 = new Tab3RecyclerAdapter(items,getActivity());
//        recyclerView2.setAdapter(adapter2);
//
//        recyclerView3 = view.findViewById(R.id.recyclerview3);
//        adapter3 = new Tab3RecyclerAdapter(items,getActivity());
//        recyclerView3.setAdapter(adapter3);
    }
}
