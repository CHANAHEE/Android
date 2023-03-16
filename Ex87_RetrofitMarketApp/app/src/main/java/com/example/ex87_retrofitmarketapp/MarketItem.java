package com.example.ex87_retrofitmarketapp;

public class MarketItem {
    // 17_ DB 의 Market 테이블의 column 값들을 저장할 멤버변수를 지정하자.
    String no;
    String name;
    String title;
    String msg;
    String price;
    String image;
    String date;

    public MarketItem() {
    }

    public MarketItem(String no, String name, String title, String msg, String price, String image, String date) {
        this.no = no;
        this.name = name;
        this.title = title;
        this.msg = msg;
        this.price = price;
        this.image = image;
        this.date = date;
    }
}
