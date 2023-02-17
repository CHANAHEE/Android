package com.example.tp08_todaymyhome;

public class Item {
    String category, title, name, scrap, view;
    int mainImg, bookMark, newImg, icon;

    public Item(String category, String title, String name, String scrap, String view, int mainImg, int bookMark, int newImg, int icon) {
        this.category = category;
        this.title = title;
        this.name = name;
        this.scrap = scrap;
        this.view = view;
        this.mainImg = mainImg;
        this.bookMark = bookMark;
        this.newImg = newImg;
        this.icon = icon;
    }
}
