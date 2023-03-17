package com.example.ex90_firebasechat;

public class MessageItem {

    // 6_ 이 객체를 이용하여 firebase DB 에 사용하려면 반드시 멤버변수를 public 으로 지정해주어야 한다.
    public String name;
    public String message;
    public String profileUrl;
    public String time;

    public MessageItem() {
    }

    public MessageItem(String name, String message, String profileUrl, String time) {
        this.name = name;
        this.message = message;
        this.profileUrl = profileUrl;
        this.time = time;
    }
}
