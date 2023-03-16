package com.example.ex87_retrofitmarketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ex87_retrofitmarketapp.databinding.RecyclerItemviewBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.VH> {

    Context context;
    ArrayList<MarketItem> items;

    public MarketAdapter(Context context, ArrayList<MarketItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.recycler_itemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MarketItem item = items.get(position);

        holder.binding.tvTitle.setText(item.title);
        holder.binding.tvMsg.setText(item.msg);
        holder.binding.tvPrice.setText(item.price + "원");

        String baseAddr = "";
        if(item.image != null) baseAddr = "http://tjdrjs0803.dothome.co.kr/RetrofitMarket/" + item.image;
        Glide.with(context).load(baseAddr).error(R.drawable.penguins).into(holder.binding.iv);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        RecyclerItemviewBinding binding;
        public VH(@NonNull View itemView) {
            super(itemView);
            binding = RecyclerItemviewBinding.bind(itemView);

            itemView.setOnLongClickListener(view -> {
                // 현재 클릭한 아이템을 얻어오자.
                MarketItem item = items.get(getLayoutPosition());

                // Retrofit 을 이용하여 DB 에서 아이템을 삭제요청하자
                Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
                RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                Call<String> call = retrofitService.deleteItem(item.no);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String s = response.body();
                        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

                        items.remove(item);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            });
        }
    }
}
