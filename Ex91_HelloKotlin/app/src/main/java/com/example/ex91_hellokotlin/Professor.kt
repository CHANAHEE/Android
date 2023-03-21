package com.example.ex91_hellokotlin

// 19_ 보조 생성자를 이용하여 상속 받아보기
// 20_ 근데 자식 클래스가 주생성자가 없다면! 상속해주는 클래스의 주생성자 호출을 쓰지 않는다.
class Professor : Person {

    var subject:String? = null // Nullable 변수!
    constructor(name:String, age:Int, subject:String):super(name,age){
        println("Create Professor Instance")
        this.subject = subject
    }

    override fun show() {
        super.show()
        println("subject : $subject")
    }
}