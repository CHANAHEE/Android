package com.cha.mvp.presenter

import android.content.Context
import com.cha.mvp.model.Item

// 6_ View 와 Presenter 의 역할을 하는 클래스들이 가지고 있어야할 기능을 정하는 인터페이스
interface MainContract {

    // 7_ # View 역할을 하는 클래스가 가져야할 기능을 기술한 인터페이스
    interface View{
        fun showData(item: Item) // 9_ model 의 데이터를 화면에 보여주는 기능 (추상메소드)
        fun getContext():Context // 10_ Presenter 에서 사용할 수 있는 Context 를 return 해주는 기능 (추상메소드)
    }
    // 8_ # Presenter 역할을 하는 클래스가 가져야할 기능을 기술한 인터페이스
    interface Presenter{
        // 11_ 사용자의 이벤트에 따라 처리할 기능 [ view 역할 클래스의 요청에 의해 실행될 메소드 ]
        fun clickSave(name: String, email: String) // 12_ save 버튼을 클릭했을 때.
        fun clickLoad() // 13_ load 버튼을 클릭했을 때.

        // 14_ Presenter 클래스 만들러가자!
    }
}