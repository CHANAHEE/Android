package com.example.ex91_hellokotlin


// 16_ 상속해주는 Person 클래스에 name 과 age 프로퍼티가 존재함.
// 그러니 주생성자에서 또 name 과 age 프로퍼티를 만들면 안됨.
// 매개변수로만 name, age 를 받아오자. 받아온다음에, Person 생성자 파라미터로 전달하자.. 아우 이상해
open class Student constructor(name:String, age:Int, var major:String): Person(name, age){

    init {
        println("Create Student Instance")
    }

    // 17_ override 키워드가 있는 메소드는 기본 open method 임.
    override fun show(){
        super.show()
        println("major : $major")
    }
}