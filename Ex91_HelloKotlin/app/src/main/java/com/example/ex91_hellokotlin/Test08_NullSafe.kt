package com.example.ex91_hellokotlin

fun main(){
    // 1_ 코틀린 언어의 주요 특징 중 하나.
    // Null Pointer Exception[NPE] 에 대한 안정성을 보유한 언어

    // 2_ 코틀린은 null 값을 저장할 수 있는 타입을 명시적으로 구분하여 사용하도록 하고 있음.
    var s1:String // 3_ non nullable 변수
    var s2:String? = null // 4_ nullable 변수

    // 5_ nullable 변수들을 사용할 때 특이한 멤버접근 연산자들이 있다.
    var str1:String = "Hello" // 6_ non nullable variable
    var str2:String? = "Nice" // 7_ nullable variable

    // 8_ 멤버 사용법의 차이
    println("글자 수 : ${str1.length}")
    //println("글자 수 : ${str2.length}") // ERROR! - 9_ null 일 수도 있으니 막 사용하지 말라고 에러표시!!
    // 10_ 해결방법 : null 이 아닐때만 하도록 하자
    if(str2 != null){
        println("글자 수 : ${str2.length}")
    }
    // 11_ 근데 if 문을 이용해 처리하는 방법은 너무 귀찮지.. 그래서 나온게 멤버접근 연산자!!
    // 12_ ?. 연산자 : null safe 연산자
    println("글자 수 : ${str2?.length}") // 13_ 만약 null 이 아니면 length 값을 가져올 수 있다.
    str2 = null
    println("글자 수 : ${str2?.length}") // 14_ 만약 null 이면 그냥 null 로 나온다.

    // 15_ 객체가 null 일 때 그냥 null 로 값이 전달되는게 싫고
    // 내가 원하는 값으로 나왔으면 한다면?
    // 16_ 객체가 null 이면 길이값이 -1 이 나오게 해보자.
    val len:Int = if(str2 != null) str2.length else -1
    println(len)

    // 17_ if-else 문이 번거롭다면..
    // 18_ ?: 연산자 - 엘비스 연산자
    val len2:Int = str2?.length ?: -1
    println(len2)

    // 19_ 이런식으로 NPE 에 안전한 연산자 기능이 있지만, 그냥 null 이면 예외가 났으면 좋겠다면?
    // 다시 말해, 저게 null 이 아님을 확신할 때 사용하는 연산자!
    // 20_ non-null asserted call
    var ss:String? = "Hello"
    println("글자 수 : ${ss!!.length}")

    var sss:String? = null
    //println("글자 수 : ${sss!!.length}")
    println()

    // 23_ non-nullable 변수에 nullable 변수를 넣을 때 필요..
    var mmm:String? = "Nice"
    var nnn:String = mmm!! // 21_ 이럴 때 사용...

    var ttt:String = "Good"
    var xxx:String? = ttt // 22_ 이건 그냥 OK
    println()

    // 24_ 안전한 캐스팅(형변환) 연산자 as? - 자료형이 맞지 않는 타입을 억지로 형변환 하는 경우에 사용
    val yyy:YYY? = YYY()

    //val zzz:ZZZ? = yyy as ZZZ // 25_ 억지로 형변환 하면 에러표시는 없지만 Exception 발생
    // 26_ 안전하게 형변환을 수행하는 연산자
    val zzz:ZZZ? = yyy as? ZZZ // 27_ 형변환이 불가하면 에러가 아니라 null 값을 전달.
}

class YYY{
    var a = 10

}

class ZZZ{
    var a = 20
}