package com.example.myrecipeapp;

import android.graphics.Bitmap;

import java.net.URI;

public class Item {
    String mainImg;
    String title;
    String hash;
    public Item() {
    }

    public Item(String mainImg, String title,String hash) {
        this.mainImg = mainImg;
        this.title = title;
        this.hash = hash;
    }
}
