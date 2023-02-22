package com.example.ex41_bottomnavigationview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Tab3RecyclerAdapter extends RecyclerView.Adapter<Tab3RecyclerAdapter.VH> {

    ArrayList<Tab3RecyclerViewItem> items;
    Context context;

    public Tab3RecyclerAdapter(ArrayList<Tab3RecyclerViewItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.itemview_recyclerview_tab3,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Tab3RecyclerViewItem item = items.get(position);
        holder.iv.setImageResource(item.img);
        holder.tv.setText(item.name);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
