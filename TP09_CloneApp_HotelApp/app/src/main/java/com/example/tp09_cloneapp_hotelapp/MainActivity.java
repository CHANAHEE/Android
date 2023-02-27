package com.example.tp09_cloneapp_hotelapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Item> items,items_domestic,items_abroad;
    RecyclerViewMainIconAdapter adapter,adapter2;
    RecyclerView recyclerView;
    Toolbar toolbar;
    CollapsingToolbarLayout ctl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        items = new ArrayList<>();
        items_abroad = new ArrayList<>();

        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));

        items_abroad.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items_abroad.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));
        items_abroad.add(new Item("프리미엄블랙",R.mipmap.ic_launcher_round));

        adapter = new RecyclerViewMainIconAdapter(this,items);

        recyclerView = findViewById(R.id.rcv_icon_1);
        recyclerView.setAdapter(adapter);

        adapter2 = new RecyclerViewMainIconAdapter(this,items_abroad);

        recyclerView = findViewById(R.id.rcv_icon_2);
        recyclerView.setAdapter(adapter2);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}