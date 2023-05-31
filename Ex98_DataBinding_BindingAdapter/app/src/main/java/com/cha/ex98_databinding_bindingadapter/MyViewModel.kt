package com.cha.ex98_databinding_bindingadapter

import androidx.databinding.ObservableField
import java.util.Date

class MyViewModel {

    // 5_ 이미지의 URL 변수 [ 관찰 가능한 타입 ]
    var img: ObservableField<String> = ObservableField("https://cdn.pixabay.com/photo/2015/07/05/09/31/path-832031_640.jpg")

    // 16_ 버튼 클릭 이벤트 콜백 함수 등록하기
    fun changeImage(){
        img.set("https://cdn.pixabay.com/photo/2019/03/31/14/31/houses-4093227_640.jpg")
    }

    // 18_ 리사이클러뷰가 보여줄 대량의 데이터 컬렉션 : 2개 정도의 재료를 가지고 초기화
    val items: ObservableField<MutableList<Item>> = ObservableField(mutableListOf( Item("sam","Hello"), Item("robin","Nice")))



    // 25_ 버튼 클릭 이벤트 콜백 에서 호출하는 메소드
    fun addItem(){
//        //원래는 서버에서 새로운 데이터를 읽어오는 코드...
//        //테스트 목적으로 그냥 Item 추가
//        val list= items.get()
//        list?.add(0, Item("NEW", Date().toString()))
//        items.set(list) // 같은 객체를 다시 설정하면 화면 갱신 안됨. Observable 의 특징. 값을 바꿔줘야함. 그래서 다른 방법 사용

        val list: MutableList<Item> = mutableListOf()
        list.add(Item("NEW", Date().toString()))
        list.addAll(items.get()!!)
        items.set(list)
    }

}

// 26_ ObservableXXX 는 몇가지 단점이 있음.
// (1) 새로 set 하는 객체가 변경되지 않으면 화면갱신이 안됨.
// (2) 액티비티나 프래그먼트의 라이프사이클을 고려하지 않고 데이터 변경에 반응함. 화면이 종료되는 상황에서도 화면갱신을 시도함.
// 이런 단점을 개선하기 위해 Jetpack 라이브러리로 배포된 LiveData 라는 녀석이 등장.