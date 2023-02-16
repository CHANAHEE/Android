package com.example.ex25_listviewcustom;

// 2_ 대량의 데이터를 위한 클래스
public class Item {

    String name; // 전현무
    String nation; // 대한민국
    int imgId; // 국기 R.drawable.korea

    // 3_ 객체 생성시 자동으로 실행되는 메서드 - 생성자
    public Item(String name,String nation, int imgId){
        this.name = name;
        this.nation = nation;
        this.imgId = imgId;
    }
}
