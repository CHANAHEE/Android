package com.cha.ex97_databinding

import androidx.databinding.ObservableField


// 26_ MVVM 패턴의 View 에서 사용할 데이터(Model) 을 연결해주는 ViewModel 역할의 클래스 정의
class MyDataViewModel {

    // 27_ 이미지뷰에서 보여줄 이미지 source URL (문자열 경로)
    val img: ObservableField<String> = ObservableField("https://cdn.pixabay.com/photo/2018/05/17/09/18/away-3408119_640.jpg")

    // 31_ 리사이클러뷰가 사용할 대량의 데이터
    var mItems = mutableListOf<String>()
    val items: ObservableField<MutableList<String>> = ObservableField(mItems)
}