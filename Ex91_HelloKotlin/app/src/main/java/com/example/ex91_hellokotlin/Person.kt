package com.example.ex91_hellokotlin


// 14_ Person 클래스 생성!
open class Person constructor(var name:String,var age:Int){

    init {
        println("Create Person Instance")
    }

    open fun show(){
        println("name : $name")
        println("age : $age")
    }
}