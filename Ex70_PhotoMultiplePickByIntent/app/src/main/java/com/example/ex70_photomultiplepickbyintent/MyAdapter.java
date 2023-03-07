package com.example.ex70_photomultiplepickbyintent;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Uri> images;

    public MyAdapter(Context context, ArrayList<Uri> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 6_ 레이아웃 인플레이터를 데꼬와서 인플레이트할것.
        // 7_ 이 메서드의 목적은 뷰 홀더 객체를 만드는것. 만들어진 뷰홀더 객체는 onBindViewHolder 로 넘어감.
        return new VH(LayoutInflater.from(context).inflate(R.layout.page,parent,false));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        // holder.pv.setImageURI(images.get(position));
        // Glide 를 쓰는게 여러방면에서 좋다. gif 도 표현할 수 있음.
        Glide.with(context).load(images.get(position)).into(holder.pv);
    }

    class VH extends RecyclerView.ViewHolder{

        PhotoView pv;

        public VH(@NonNull View itemView) {
            super(itemView); // 5_ 여기서 아이템뷰는 page.xml 의 상대레이아웃임.
            pv = itemView.findViewById(R.id.pv);
        }
    }
}
