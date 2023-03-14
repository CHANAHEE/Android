package com.example.ex82_httprequest;

public class Item {
    // 30_ 데이터베이스의 데이터를 가져와 저장시켜놓을 클래스 준비
    int no;
    String name;
    String msg;
    String date;

    public Item(){

    }
    public Item(int no, String name, String msg, String date) {
        this.no = no;
        this.name = name;
        this.msg = msg;
        this.date = date;
    }
}
