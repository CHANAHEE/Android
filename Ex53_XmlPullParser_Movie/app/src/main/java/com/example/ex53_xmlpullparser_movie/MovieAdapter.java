package com.example.ex53_xmlpullparser_movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import kotlin.jvm.internal.Lambda;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.VH> {

    Context context;
    ArrayList<MovieItem> movieItems;

    public MovieAdapter(Context context, ArrayList<MovieItem> movieItems) {
        this.context = context;
        this.movieItems = movieItems;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.recyclerview_movieitem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MovieItem movieItem = movieItems.get(position);

        holder.tvRank.setText(movieItem.rank);
        holder.tvTitle.setText(movieItem.movieNm);
        holder.tvOpenDt.setText(movieItem.openDt);
        holder.tvAudiAcc.setText(movieItem.audiAcc);
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tvRank,tvTitle,tvOpenDt,tvAudiAcc;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvRank = itemView.findViewById(R.id.tv_rank);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOpenDt = itemView.findViewById(R.id.tv_open_dt);
            tvAudiAcc = itemView.findViewById(R.id.tv_audi_acc);
        }
    }
}
