package com.example.myrecipeapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerGridLayoutAdapter extends RecyclerView.Adapter<RecyclerGridLayoutAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public RecyclerGridLayoutAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.recyclerview_iconmenu,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item = items.get(position);
        holder.imgBtn.setImageResource(item.icon);
        holder.tv.setText(item.name);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageButton imgBtn;
        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);

            imgBtn = itemView.findViewById(R.id.icon_imgbtn);
            tv = itemView.findViewById(R.id.icon_title);

            imgBtn.setOnClickListener(listener);
            tv.setOnClickListener(listener);
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isSelected()){
                    tv.setTextColor(Color.parseColor("#000000"));
                    imgBtn.setAlpha(1f);
                    v.setSelected(false);
                }else{
                    v.setSelected(true);
                    imgBtn.setAlpha(0.1f);
                    tv.setTextColor(Color.parseColor("#D3840F"));
                }


            }
        };
    }
}
