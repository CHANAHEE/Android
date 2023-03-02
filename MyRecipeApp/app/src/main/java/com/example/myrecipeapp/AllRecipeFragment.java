package com.example.myrecipeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AllRecipeFragment extends Fragment {

    ArrayList<Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    SQLiteDatabase database;
    MainActivity mainActivity;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_allrecipe,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_allrecipe);
        adapter = new RecyclerAdapter(getActivity(),items);
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.fab).setOnClickListener(v -> {
            recyclerView.scrollToPosition(RecyclerView.SCROLLBAR_POSITION_DEFAULT);
        });

        new Thread(){
            @Override
            public void run() {
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        database = SQLiteDatabase.openDatabase("/data/data/com.example.myrecipeapp/databases/recipe.db",null,SQLiteDatabase.OPEN_READONLY);
                        Cursor cursor = database.rawQuery("SELECT * FROM recipe",null);

                        if(cursor == null) return;

                        int row = cursor.getCount();
                        cursor.moveToFirst();

                        for(int i=0;i<row;i++){
                            Item item = new Item();
                            item.title = cursor.getString(1);
                            item.mainImg = cursor.getString(2);
                            item.hash = cursor.getString(3);

                            items.add(item);
                            cursor.moveToNext();
                        }

                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }
}
