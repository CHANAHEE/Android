package com.example.ex92_kotlinrecyclerview


// 10_ 대량의 데이터를 저장할 클래스!!

// 11_ data class : 데이터만 저장하는 목적의 클래스
// 일반클래스와 다르게 자동으로 equals() 호출 시, 객체 주솟값이 아니라 멤버값을 비교하도록 설계된 클래스
// 단, 주생성자의 데이터만 비교한다!
data class Item constructor(var name:String,var msg:String,var imgId:Int)