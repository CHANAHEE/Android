package com.cha.ex99_jetpack_livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.Date

class MyViewModel {
    // 1_ Livedata : ObservableXXX 와 다르게 라이프 사이클에 따라 효율적으로 UI 갱신

    val name: MutableLiveData<String> = MutableLiveData("Hello")
    val age: MutableLiveData<Int> = MutableLiveData(20)


    // 2_ 기본적으로 Observable 을 사용했을 때와 마찬가지로 DataBinding 을 사용할 수 있다.
    // MainActivity 에서도 setContentView 도 하고, activity_main 에서 data 도 만들고 등등.

    // 3_ 데이터 변경 콜백메소드를 만들자.
    fun changeName(){
        name.value = "robin"
        // 4_ 이렇게만 하면 원래 Observable 에서는 UI 갱신이 된다. 하지만, LiveData 에서는 이 부분에 대한 선택권을 주는 느낌.
        // 그래서 화면 갱신을 위해서는 추가적인 작업이 필요하다. 2가지 방법으로 가능.

        // 5_ LiveData 변수를 관찰하는 observe() 메소드를 통해 관찰 및 UI 갱신 -> MainActivity 로 가자.
    }
    // 11_ 한가지 방법은 알아봤으니.. 2번째 방법을 알아보자.
    // 두번째 방법은 LiveData 의 변화를 반영할 LifecycleOwner 를 미리 지정해놓는것. -> MainActivity 로 가자.

    // 13_ 이렇게 해주면 나이가 올라간다.
    fun changeAge(){
        age.value = age.value!!.toInt() + 1
    }


    // 14_ 리사이클러 뷰 LiveData 변수 적용 -> Item 클래스 만들자. 리사이클러가 보여줄 뷰 만들고, 어댑터 만들자.
    val items: MutableLiveData<MutableList<Item>> = MutableLiveData(mutableListOf(Item("sam","hello")))


    // 18_ 아이템 추가 함수
    fun addItem(){
        val list = items.value
        list?.add(0,Item("robin",Date().toString()))
        //items.value = list
        items.postValue(list) // 19_ 별도 Thread 로 설정작업
    }
}