package com.example.myrecipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(new Item(R.drawable.ic_action_home,"Sample"));
        items.add(new Item(R.drawable.ic_action_home,"Sample"));
        items.add(new Item(R.drawable.ic_action_home,"Sample"));
        items.add(new Item(R.drawable.ic_action_home,"Sample"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview_home);
        adapter = new RecyclerAdapter(getActivity(),items);
        recyclerView.setAdapter(adapter);
    }
}
