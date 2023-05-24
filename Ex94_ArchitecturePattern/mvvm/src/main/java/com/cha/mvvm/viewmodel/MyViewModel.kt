package com.cha.mvvm.viewmodel

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import com.cha.mvvm.model.Item
import com.cha.mvvm.model.ItemModel

class MyViewModel(context: Context){ // context 는 매개변수.. 원래는 context 를 받지 않는다고 함.

    // 8_ view 와 연결할 model 역할의 클래스 참조변수
    var itemModel: ItemModel = ItemModel(context)

    // 9_ 값 변경이 관찰되는 데이터 멤버변수 ObservableXXX
    var model: ObservableField<Item> = ObservableField()

    init{
        model.set(Item("no name","no email"))
    }

    // 12_ EditText 의 글씨를 가지고 있을 일반 변수를 만들자.
    private var name: String = ""
    private var email: String = ""

    // 13_ EditText의 글씨가 변경될 때 마다 반응하도록 등록한 메소드 2개
    fun changeName(s: CharSequence?, start: Int, end: Int, count: Int){
        this.name = s.toString()
    }

    fun changeEmail(s: CharSequence?, start: Int, end: Int, count: Int){
        this.email = s.toString()
    }

    // 10_ view 의 이벤트에 반응하여 model 을 제어하도록 요청하는 기능 메소드들을 만들자.
    fun clickSave(view: View){
        itemModel.saveData(name,email)
    }

    fun clickLoad(view: View){
        var item = itemModel.loadData()
        model.set(item)
    }
}