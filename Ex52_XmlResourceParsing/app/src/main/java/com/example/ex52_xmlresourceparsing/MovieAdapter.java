package com.example.ex52_xmlresourceparsing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public MovieAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VH vh = new VH(LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item = items.get(position);
        holder.tv1.setText(item.title);
        holder.tv1.setText(item.num);
        holder.tv1.setText(item.genre);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class VH extends RecyclerView.ViewHolder{

        TextView tv1,tv2,tv3;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv1 = itemView.findViewById(R.id.tv2);
            tv1 = itemView.findViewById(R.id.tv3);
        }
    }
}
