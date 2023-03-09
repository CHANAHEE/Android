package com.example.ex78_viewbinding;

public class ItemVO {
    String title; // 16_ 제목글씨
    int imgResId; // 17_ 이미지의 리소스 ID

    public ItemVO() {
    }

    public ItemVO(String title, int imgResId) {
        this.title = title;
        this.imgResId = imgResId;
    }
}
