package com.example.ex28_recyclerview2;

// 2_ 대량의 데이터
public class Item {
    String name;    // 루피
    String role;    // 선장
    int profile;    // 프로필 R.drawble.crew_luffy
    int imgId;      // 이미지 R.drawble.bg_one01.jpg

    public Item(String name, String role, int profile, int imgId) {
        this.name = name;
        this.role = role;
        this.profile = profile;
        this.imgId = imgId;
    }
}
