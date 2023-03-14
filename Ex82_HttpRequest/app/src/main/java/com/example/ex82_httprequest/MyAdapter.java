package com.example.ex82_httprequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ex82_httprequest.databinding.RecylcerItemviewBinding;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.recylcer_itemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item = items.get(position);

        holder.binding.tvNo.setText(item.no+"");
        holder.binding.tvName.setText(item.name);
        holder.binding.tvMsg.setText(item.msg);
        holder.binding.tvDate.setText(item.date);

        // 33_ 어댑터 준비 끝. BoardActivity 로 가자.
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        RecylcerItemviewBinding binding;

        public VH(@NonNull View itemView) {
            super(itemView);
            binding = RecylcerItemviewBinding.bind(itemView);
        }
    }
}
