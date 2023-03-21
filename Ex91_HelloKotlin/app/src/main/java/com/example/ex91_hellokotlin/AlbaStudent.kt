package com.example.ex91_hellokotlin

// 22_ 주 생성자의 constructor 키워드 생략
class AlbaStudent(name:String, age:Int, major:String, var task:String) : Student(name, age, major){

    init {
        println("Create AlbaStudent Instance")
    }

    override fun show() {
        super.show()
        println("task : $task")
    }
}