package com.example.tp07_memberlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.listview_layout,null);
        }

        Item item = items.get(i);

        ImageView flag = view.findViewById(R.id.flag_listview);
        ImageView profile = view.findViewById(R.id.profile_listview);
        TextView name = view.findViewById(R.id.name_listview);
        TextView nation = view.findViewById(R.id.nation_listview);

        flag.setImageResource(item.flag);
        profile.setImageResource(item.profile);
        name.setText(item.name);
        nation.setText(item.nation);

        return view;
    }
}
