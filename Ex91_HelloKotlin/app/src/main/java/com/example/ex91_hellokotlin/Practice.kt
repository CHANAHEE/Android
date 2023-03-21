package com.example.ex91_hellokotlin


fun main(){

    var map:Map<String,String> = mapOf(Pair("title","Android"),Pair("size","big"))
    map.forEach {
        println(it)
    }



}
