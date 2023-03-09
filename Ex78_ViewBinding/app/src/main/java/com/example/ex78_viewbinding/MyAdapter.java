package com.example.ex78_viewbinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex78_viewbinding.databinding.RecyclerItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {
    RecyclerItemBinding binding;
    Context context;
    ArrayList<ItemVO> items;

    public MyAdapter(Context context, ArrayList<ItemVO> items) {

        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        binding = RecyclerItemBinding.inflate(LayoutInflater.from(context),parent,false);
//        return new VH(binding.getRoot());

        return new VH(LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ItemVO itemVO = items.get(position);
        holder.binding.iv.setImageResource(itemVO.imgResId);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        // 20_ recycler_item.xml 과 연결되는 바인딩 클래스 참조변수
        RecyclerItemBinding binding;

        public VH(@NonNull View itemView) {
            super(itemView);
            // 21_ 이미 만든 뷰객체 안에 있는 자식뷰들과 연결하는 바인딩 객체를 만들자.
            binding = RecyclerItemBinding.bind(itemView);
        }
    }
}
