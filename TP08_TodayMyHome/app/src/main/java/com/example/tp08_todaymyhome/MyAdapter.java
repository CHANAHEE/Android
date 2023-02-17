package com.example.tp08_todaymyhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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
        View layoutInflater = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        VH vh = new VH(layoutInflater);
        return vh;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item = items.get(position);

        holder.rcv_category.setText(item.category);
        holder.rcv_view.setText(item.view);
        holder.rcv_name.setText(item.name);
        holder.rcv_scrap.setText(item.scrap);
        holder.rcv_title.setText(item.title);

        holder.rcv_imgBtn_bookMark.setImageResource(item.bookMark);
        holder.rcv_mainImg.setImageResource(item.mainImg);
        holder.rcv_newImg.setImageResource(item.newImg);
        holder.rcv_icon.setImageResource(item.icon);
    }

    class VH extends RecyclerView.ViewHolder {
        ImageButton rcv_imgBtn_bookMark;
        ImageView rcv_newImg;
        ImageView rcv_mainImg;
        CircleImageView rcv_icon;
        TextView rcv_category;
        TextView rcv_title;
        TextView rcv_name;
        TextView rcv_scrap;
        TextView rcv_view;
        
        
        public VH(@NonNull View itemView) {
            super(itemView);
            rcv_imgBtn_bookMark = itemView.findViewById(R.id.rcv_bookMark);
            rcv_newImg = itemView.findViewById(R.id.rcv_new);
            rcv_mainImg = itemView.findViewById(R.id.rcv_mainImg);
            rcv_icon = itemView.findViewById(R.id.rcv_civ_icon);
            rcv_category = itemView.findViewById(R.id.rcv_category);
            rcv_title = itemView.findViewById(R.id.rcv_title);
            rcv_name = itemView.findViewById(R.id.rcv_name);
            rcv_scrap = itemView.findViewById(R.id.rcv_scrap);
            rcv_view = itemView.findViewById(R.id.rcv_view);
            
            rcv_imgBtn_bookMark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rcv_imgBtn_bookMark.setImageResource(R.drawable.ic_action_bookmark_click);
                    Toast.makeText(context, "북마크에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
