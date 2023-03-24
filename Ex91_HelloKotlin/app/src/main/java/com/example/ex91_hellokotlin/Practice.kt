package com.example.ex91_hellokotlin

fun main(){
    val parent:Parent = Parent("sam")
    val child:Child = Child("robin",25)
}

open class Parent(var name:String){
    init{
        println("Parent 의 주 생성자 : $name")
    }

}

class Child(name:String, var age:Int) : Parent(name){
    init{
        println("Child 의 주 생성자 : $name , $age")
    }

}