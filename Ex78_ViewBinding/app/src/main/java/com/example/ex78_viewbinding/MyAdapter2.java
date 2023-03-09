package com.example.ex78_viewbinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex78_viewbinding.databinding.RecyclerItemBinding;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.VH> {

    Context context;
    ArrayList<ItemVO> items;
    RecyclerItemBinding binding;

    public MyAdapter2(Context context, ArrayList<ItemVO> items) {
        this.context = context;
        this.items = items;

    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RecyclerItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        binding.iv.setImageResource(items.get(position).imgResId);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class VH extends RecyclerView.ViewHolder{

        public VH(@NonNull RecyclerItemBinding binding) {
            super(binding.getRoot());
        }
    }
}
