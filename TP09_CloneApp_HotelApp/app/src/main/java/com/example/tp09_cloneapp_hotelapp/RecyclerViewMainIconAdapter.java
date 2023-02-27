package com.example.tp09_cloneapp_hotelapp;

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

public class RecyclerViewMainIconAdapter extends RecyclerView.Adapter<RecyclerViewMainIconAdapter.VH> {


    Context context;
    ArrayList<Item> items = new ArrayList<>();

    public RecyclerViewMainIconAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_mainicon_itemview,parent,false);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item = items.get(position);
        holder.iv.setImageResource(item.icon);
        holder.tv.setText(item.category);

    }

    class VH extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;
        public VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_category);
            iv = itemView.findViewById(R.id.iv_icon);
        }
    }
}
