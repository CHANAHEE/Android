package com.example.ex91_hellokotlin

fun main(){
    // 84_ 배열 Array & Collection API
    /*
    *
    *
    *      배열 - 요소 개수변경이 불가능한 배열 : Array
    *
    *
    * */
    // 85_ var aaa = arrayOf(10,20,30) : 자동 추론으로 배열을 만들었음.
    // 86_ 제네릭 사용할 때 주의점! 제네릭 <> 다음에 = 가 붙어있으면 안됨. 꼭 띄어쓰기 할것!
    var aaa:Array<Int> = arrayOf(10,20,30)
    // 87_ 요소값 출력
    println(aaa[0])
    println(aaa[1])
    println(aaa[2])

    // 88_ 요소값 변경도 특별할 것 없음
    aaa[0] = 100
    println(aaa[0])
    println()

    // 89_ 배열의 길이값 멤버변수 : Java 의 배열은 length, 코틀린에서는 size 변수가 생겼음
    println("배열의 길이 : ${aaa.size}")
    println()

    // 90_ 요소값을 일일이 접근하는것 짜증. 반복문 활용!
    for(i in 0 until aaa.size){
        println(aaa[i])
    }
    println()

    // 91_ 배열은 어차피 연속된 인덱스 번호의 나열이니까
    // 0..2 이거나 배열변수 size 를 놓으나 같은 개념일것.
    // 마치 자바의 확장된 for 문 처럼 다음과 같이 작성 가능
    for( t in aaa){
        // 92_ 주의할 점은 t 는 인덱스가 아니라 요소값임.
        println(t)
    }
    println()

    // 93_ 향상된 for 문법을 사용하면서도 index 번호로 반복하고 싶다면
    for( i in aaa.indices){
        println(i)
    }
    println()

    // 94_ 인덱스와 값을 동시에 가져오고 싶다면...
    for( (v,i) in aaa.withIndex()){
        println("$i : $v")
    }
    println()

    // 95_ 배열 객체 멤버안에 요소값 각각을 반복적으로 접근할때마다
    // { } 안에 있는 코드가 자동으로 발동하는 forEach 기능이 있다.

    // 96_ {} 안에 it 이라는 특별한 매개변수가 존재함.
    // 그 it 이 각 요소 참조변수 임
    aaa.forEach {
        println(it)
    }
    println()

    var bbb:Array<String> = arrayOf("aaa","bbb","ccc")
    bbb.forEach {
        println(it)
    }
    println()

    // 97_ 배열을 만들면서 자동추론을 적용할 때 타입을 명시하는데,
    // 기본형 타입에 대해서는 별도의 생성 함수가 존재함.
    var ccc = arrayOf<Int>(10,20,30)

    var ddd = intArrayOf(10,20,30) // 98_ Int 전용배열
    var eee = doubleArrayOf(3.14,4.44,5.55) // 99_ Double 전용배열
    // 100_ String 에 대한 별도의 생성함수는 없음. 기본형 타입만 있음.

    // 101_ 빈배열 5개 짜리 만들어보자.
    var fff = arrayOf<Int>() // 102_ 이거는 0개짜리 배열
    var ggg = arrayOf<Int>(0,0,0,0,0) // 103_ 요고는 5개 짜리.
    // 104_ 100개짜리를 미리 만들고싶다면?
    // 배열 요소값의 시작을 그냥 null 값을 주며 개수를 지정
    var hhh = arrayOfNulls<Double>(5)
    for(t in hhh){
        println(t)
    }
    println()

    // 105_ 즉, 배열은 요소의 개수변경이 불가한 특징을 가지고 있음!

    /*
    *
    *
    *
    *       컬렉션 - List, Set, Map 특성의 대량의 데이터들
    *
    *
    * */
    // 106_ List : 요소가 순서대로 저장됨. 인덱스 번호 자동부여. 중복데이터 허용.
    // 107_ Set : 요소가 순서대로 있지 X. 인덱스 번호가 없음. 중복데이터 불가.
    // 108_ Map : 요소가 순서대로 있지 X. 인덱스 번호 대신 키값 사용. 중복키는 불가. 단, 키값만 다르다면 중복 값은 허용.
    // 109_ 코틀린의 Collection 들은 모두 요소의 추가 삭제 및 변경이 불가한 종류와 가능한 종류로 나눠짐.

    // 110_ 요소개수 추가/삭제/변경 모두 불가한 컬렉션 : listOf(), setOf(), mapOf() 로 만든다 - (1)
    // 111_ 요소개수 추가/삭제/변경 모두 가능한 컬렉션 : mutableListOf(),mutableSetOf(),mutableMapOf() 로 만든다. - (2)



    // (1)

    // 112_ List
    val list: List<Int> = listOf(10,20,30,20) // 중복가능
    for(i in list){
        println(i)
    }
    println()

    // 113_ 값의 추가/삭제/변경이 불가 -> 관련한 어떠한 기능메소드도 없음

    // 114_ Set
    val set:Set<Double> = setOf(3.14,5.55,2.22, 5.55) // 중복 불가
    for( e in set ) println(e)
    println()

    // 115_ Map
    // 116_ Pair() 객체를 이용하여 키- 밸류 지정
    val map:Map<String,String> = mapOf(Pair("title","hello"),Pair("msg","nice to meet you"))
    println("요소의 개수 : ${map.size}")
    for((key,value) in map){
        println("${key} : ${value}")
    }
    println()

    // 117_ to 연산자를 이용하여 키 - 밸류 지정
    val map2 :Map<String,String> = mapOf("id" to "tjdrjs", "password" to "1234")
    for((k,v) in map2) println("${k} : ${v}")


    // (2)

    // 118_ MutableList
    val mList:MutableList<Int> = mutableListOf(10,20,30)
    println("요소의 개수 : ${mList.size}")
    mList.add(40)
    mList.add(0,50)
    println("요소의 개수 : ${mList.size}")

    mList[1] = 200 // 119_ 마치 배열처럼 요소값에 접근하는 것을 권장함.
    for(t in mList){
        print("${t}  ")

    }
    println()

    println("2번방의 값 : ${mList[2]}")

    // 119_ MutableSet
    val mSet:MutableSet<Double> = mutableSetOf()
    println("요소의 개수 : ${mSet.size}")
    mSet.add(5.55)
    mSet.add(3.14)
    mSet.add(-10.12)
    mSet.add(3.14)
    for(t in mSet){
        print("${t}  ")
    }
    println()

    // 120_ MutableMap
    val mMap:MutableMap<String,String> = mutableMapOf("name" to "sam","age" to "25")
    println("요소의 개수 : ${mMap.size}")
    mMap.put("address","seoul")
    for(t in mMap){
        print("${t.value}  ")
    }
    
    // 121_ mutable 에 익숙하지 않다면 자바의 ArrayList, HashSet, HashMap 에 대응되는 클래스들이 있음
    var arrList:ArrayList<Any> = arrayListOf(10,"Hello",true)
    // 122_ 사용법은 자바자 mutable 과 같음
    var hashSet:HashSet<Any> = hashSetOf(100,"good")
    var hashMap:HashMap<String,String> = hashMapOf("hello" to "hello")
}