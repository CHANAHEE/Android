package com.cha.ex97_databinding

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

// 5_ 데이터를 가진 클래스를 만들어보자.
class User {

    // 11_ 값 변경이 관찰되는 멤버변수 ObservableXXX : 기본적으로 primitive type && List or Map 나머지들은 Field...
    var name: ObservableField<String> = ObservableField("")
    var age: ObservableInt = ObservableInt(0)
    var fav: ObservableBoolean = ObservableBoolean(true)

    constructor(name: String, age: Int, fav: Boolean=true){
        this.name.set(name)
        this.age.set(age)
        this.fav.set(fav)
    }

    // 10_ change name 버튼 클릭 callback method :
    fun changeName(view: View){
        name.set("robin")
    }

    // 11_ age 변수값을 1 증가 시키는 기능 콜백 메소드
    fun increaseAge(v: View){
        age.set(age.get() + 1)
    }

    // 13_ 좋아요 true/false 변경하는 기능 메소드 -콜백용 메소드가 아닌 일반 메소드로 작성
    // 이 메소드를 xml 버튼의 onclick 속성으로 지정한 익명콜백 함수에서 대신 호출해줄것임.
    fun toggleFav(){ // 14_ 파라미터가 없음!! 잘 기억해두자.
        fav.set(!fav.get())
    }

    // 16_ 체크상태가 변경되는 것에 반응하는 콜백메소드 - 파라미터 중요!!
    fun changeFav(v: CompoundButton, isChecked: Boolean){
        Toast.makeText(v.context, "$isChecked", Toast.LENGTH_SHORT).show()
        // 17_ 체크상태값을 관리하는 fav 변수값도 변경
        fav.set(fav.get())
    }


    // 19_ EditText 의 글씨 변화값을 가지고 있을 관찰가능한 변수
    val email: ObservableField<String> = ObservableField("message")
    fun onSaveEmail(s: CharSequence?, start: Int, before: Int, count: Int){
        email.set(s.toString())
    }

    // 20_ EditText 에 글씨를 입력하고 버튼을 클릭하여 텍스트뷰에 보여주기
    private var inputValue:String = ""
    val observeValue: ObservableField<String> = ObservableField(inputValue)

    // 21_ EditText 의 글씨 변경 이벤트 콜백 메소드에 의해 호출될 일반 메소드
    fun changeInputValue(s: CharSequence){
        inputValue = s.toString()
    }

    // 22_ 작성완료버튼 클릭 콜백메소드에 의해 호출될 일반 메소드 : observable 변수에 넣기
    fun completeBtn(){
        observeValue.set(inputValue)
    }

}