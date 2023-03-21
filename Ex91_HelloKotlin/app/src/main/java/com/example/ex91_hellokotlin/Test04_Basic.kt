package com.example.ex91_hellokotlin

fun main(){
    /*
    *
    *
    *       함수
    *
    *
    * */
    // 2_ 함수 호출
    show()

    output(100,"Hello")

    var result = sum(100,200)
    println("결과값 : ${result}")

    var result2 = display()
    println(result2)

    val result3 = getData3(5)
    println(result3)

    val result4 = getData4(9)
    println(result4)

    // 11_ 변수를 이용하여 함수를 호출할 수 있다! 마치 함수를 객체처럼 바라보고, 변수를 이용해
    // 함수를 참조하는것과 같이 사용한다! 왜 사용하는지는 잘 안와닿는다...
    bbb()
    ccc()
    ddd()
    eee()
    fff("Hello")
    ggg("common")
    hhh("Good")
    iii("nice to meet you")
    jjj("sam",20)
    val result5 = kkk()

    println(ooo("kkk"))


    // 25_ 고차함수
    var f = fun(){
        println("익명함수")
    }

    f()

    var g = f
    g() // 26_ 전달 받은 함수를 변수명으로 대신 호출이 가능

    // 27_ 함수를 다른 변수에 저장할 수 있다면 다른 함수의 매개변수에도 전달이 가능하겠네!

    ppp("Hello",g)


    // 30_ 전달할 함수 만들기
    val xx:(String)->Int = {
        it.length
    }
    ttt("android",xx)
    ttt("android",{it.indexOf("d")})
    ttt("android",{s->s.length})
    // 31_ Android setOnClickListener 의 모습


    www(10,20)
    www()

    zzz("korea","seoul")
    // 33_ 파라미터를 지정하면서 대입 가능
    zzz(city = "newyork",nation = "usa")
    zzz(city = "busan")
}

fun zzz(nation:String = "korea", city:String){
    print(nation + " ")
    println(city)
}







// 32_ 함수 파라미터의 default 값
fun www(a:Int = 3, b:Int = 5){ // 33_ 기본값 지정
    println("a : ${a} , b : ${b}")
}








// 29_ 응용된 고차함수
fun ttt(s:String, g:(String)->Int){
    val result = g(s)
    println(result)
}
// 28_ 고차함수 : 함수의 파라미터로 다른 함수를 전달받는 함수
fun ppp(s:String, g:()->Unit){
    println("string : ${s}")
    g()
}






// 24_ 마무리 연습
val ooo:(String)->Int = {
    s-> s.length
}
// 23_ 축약형의 {} 안에 값이 많으면 - 마지막 값
val nnn:()->Int = {
    30
    20
    10
}
// 22_ 익명함수 축약형 - return 키워드도 생략해야만 한다!!
val mmm:()->Int = { 20 }

// 21_ 리턴이 있는 익명함수의 자료형 명시
val lll:()->Int = fun():Int{
    return 20
}
// 20_ 리턴이 있는 익명함수
val kkk = fun():Int{
    return 20
}
// 19_ 파라미터 여러개짜리 - it 이 자동으로 생기지 않음 !
val jjj:(String, Int)->Unit = {
    name, age -> println("name : ${name} - age : ${age}")
}

// 18_ 익명함수의 축약표현 - 파라미터 이름 정하기
val iii: (String)->Unit = {
    s->println("글자 수: ${s.length}")
}
// 17_ 익명함수의 축약표현
val hhh:(String)->Unit = {
    // 축약하면 자동으로 it 이라는 특별한 키워드 변수가 생김. it 이 파라미터 임.
    println("글자수 : ${it.length}")
}
// 16_ 이번에는 자료형 명시하기
val ggg :(String)->Unit = fun(s:String){
    println("글자 수 : ${s.length}")
}

// 15_ 파라미터가 있는 익명함수
val fff = fun(s:String){
    println("글자 수 : ${s.length}")
}

// 14_ 익명함수 자료형은 자동추론이 가능하다.
var eee = {
    println("익명함수4")
}

// 13_ 익명함수 축약표현
var ddd:()->Unit = {
    println("익명함수3")
}
// 12_ 익명함수를 가진 변수에 자료형 명시해보기 : 함수의 자료형은 ()->Unit 이며, 람다식으로 표기한다.
var ccc:()->Unit = fun(){
    println("익명 함수2")
}
// 10_ 익명함수 : 사용하기 위해서는 반드시 함수를 변수에 대입해야 함.
var bbb = fun(){
    println("익명 함수")
}





// 8_ 조금 더 복잡한 리턴값을 가진 함수 단순화
fun getData3(num:Int):String{
    if(num<10) return "God"
    else return "shit"
}
// 9_ 위 함수를 단순화 해보자
fun getData4(num:Int):String = if(num<10) "God" else "shit"




// 6_ 함수 선언의 단순화 : 리턴 키워드를 할당 연산자 ( = ) 로 바꿀 수 있음
fun getData():String{
    return "Hello"
}
// 7_ 위 함수를 단순화 해보자
fun getData2():String = "Hello"




// 5_ 리턴이 없는 함수의 리턴을 받을 경우
fun display(){
    println("display!!")
}




// 4_ 리턴하는 함수 호출, 단 리턴 타입을 작성하는 위치는 sum() 다음에 : 을 붙이고 작성
fun sum(n1:Int,n2:Int):Int{
    return n1+n2
}





// 3_ 파라미터로 값 전달
fun output(num:Int,str:String){ // 3_1 파라미터에 var, val 키워드 사용불가
    println(num.toString() + str)
}





// 1_ 함수 만들기
fun show(){
    print("show function")
    println()
}