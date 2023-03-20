package com.example.ex91_hellokotlin

fun main(){
    /*
    *
    *
    *    연산자
    *
    *
    * */
    // 46_ 연산자 특이점
    // 숫자 타입들간의 산술 연산은 자동 형변환이 제공된다. 작은것-->큰것
    println(10+3.14)

    // 47_ 숫자 타입이 아닌 자료형과는 자동형변환 안됨
    // println(10+true) // error!
    // println( 10 + 'A') // error!

    // 48_ 자료형을 체크하는 연산자 is
    var n = 10
    if(n is Int){
        println("n 은 Int 형 변수다!")
    }else{
        println("n 은 Int 형 변수가 아니다!")
    }

    // 49_ is 연산자로 nullable 과 non-nullable 을 구분하지 않는다.
    var n2:String = "Hello"
    if(n2 is String) println("n2 변수는 String")
    if(n2 is String?) println("n2 변수는 String?")

    // 50_ !is 도 있음
    if(n2 !is String) println("n2 변수는 String 이 아니다")

    // 51_ 다른 자료형은 is 로 체크하면 문법에러!
    // if(n2 is Int) // ERROR

    // 52_ 그러면 is 는 별 의미가 없어보이지만..
    // Any 타입에 대한 식별로 많이 사용된다!!
    var obj:Any

    obj = 10
    if(obj is Int) println("${obj} 는 Int 입니다.")
    if(obj !is String) println("${obj} 은 String 이 아닙니다")

    // 53_ 자바의 instanceof 와 같은 기능으로 사용함.

    class Person{
        var name:String = "sam"
        var age = 20

    }

    var p = Person() // Person 객체 생성
    println(p.name + "  " + p.age)

    // 54_ is 연산자의 특이 기능 - is 를 통해 어떤 객체인지 판별했다면, 참인 영역 안에서는 그 객체로 참조변수를 인식함.
    var obj2 :Any
    obj2 = Person()
    // 55_ obj2 의 자료형이 Any 타입이기 때문에 멤버변수 name 과 age 가 자동완성되지 않는다.
    if( obj2 is Person ) println( obj2.name + "  " + obj2.age)

    // 56_ 비트연산자가 없음. [&, |, ~, ^] (논리연산자는 그대로임~) 
    // 대신에 그 연산기능을 가진 메소드가 존재함
    // println(7|3) // error!
    println(7.or(3)) // 57_ 이게 OR 비트연산
    println( 7 or 3) // 58_ 마치 연산자 처럼 or 메소드 표기가능


    /*
    *
    *
    *    조건문
    *
    *
    * */
    // 59_ 조건문 : if, when [switch 문법이 없음]
    // 60_ if 표현식 - if 문이나 else 문의 마지막 실행문이 변수에 대입될 수 있음.
    var ss:String
    if(10>5){
        ss = "Hello"
    }else{
        ss = "Nice"
    }


    var sss:String = if(10>5){
        "Hello"
        print("aaaddd")
        "Good" // 62_ 실행문 안쪽의 값이 여러개라면 마지막 값이 대입된다.

    }else {
        "Nice"
    }
    // 63_ 이런 특징 때문에 if 문을 코틀린에서는 제어문이라는 표현 대신, if 표현식 이라고도 한다.
    // 64_ 그래서 코틀린에서는 삼항연산자가 없음 - if 표현식으로 대체
    var str:String = if(10>5) "aaa" else "bbb"

    println()

    // 65_ switch 문법이 없어지고 대신 when 문법이 대체함.
    var h:Any? = null
    var h2:Any = "HI"

    h = "HI"
    when(h){
        10->println("aaa")
        20->println("bbb")

        // 66_ 자료형이 다른 경우를 배치해도 됨.
        "Hello"->println(h)
        
        // 67_ 변수가 있어도 됨
        h2->println(h + " " + h2)

        // 68_ 2개 이상의 조건을 묶을 수도 있음.
        30,40 -> println("30이거나 40");

        // 69_ switch 문의 default 역할
        else -> { // 70_ 실행문이 여러줄이면 중괄호로 묶자.
            println("dddd")

        }
    }

    // 71_ when 도 if 문 처럼 표현식이라서 결과를 변수에 저장하는것이 가능하다.
    h = 20
    var result = when(h){
        10->"aaa"
        20->"bbb"
        else->{
            println("else")
            "ccc"
            "sss"
        }
    }
    println(result)

    // 72_ when 에 is 라는 키워드 연산자 사용도 가능. 참 쓰임새가 좋다~
    h = "hello"
    when(h){
        is Int->println("Int 타입")
        is String->println("String 타입")
        !is Int->println("Int 타입이 아니다.")
        else -> println("else")
    }

    // 73_ when 을 특정 수식으로 제어하고 싶을 때..
    // 주의! when 사용문법이 약간 다름.
    h = 85
    when{
        //h>=90 && h<=100 -> println("A학점 입니다") 74_ 코드가 모던하지 않다~
        h in 90..100 -> println("A학점 입니다")
        h>=80 -> println("B학점 입니다")
        h>=70 -> println("C학점 입니다")
        h>=60 -> println("D학점 입니다")
        else -> println("F학점 입니다")
    }
    println()
    ////////////////////////////////////////////////////////////////////
    /*
    *
    *
    *     반복문 : while, for
    *
    *
    * */
    // 75_ while 문은 똑같다. for 문만 보자. for 문은 완전히 다름
    // 76_ 0부터 5까지 "6번" 실행되는 반복문
    for(i in 0..5){
        println(i)
    }
    println()

    // 77_ 제어용 변수인 i 를 다른 이름으로 바꿔도 됨.
    for(a in 3..10){
        println(a)
    }
    println()

    // 78_ 제어변수 앞에 var 키워드를 추가하면 에러!!
    //for(var t in 0..5) error!!

    // 79_ 마지막 숫자 전까지 반복하려면 .. 대신에 until 키워드를 사용해야한다.
    for(i in 0 until 10){
        println(i)
    }
    println()

    // 80_ 2씩 증가( step)
    for(i in 0..10 step 2){
        println(i)
    }
    println()

    // 81_ 값의 감소( downTo )
    for(i in 10 downTo 0 step 1){
        println(i)
    }
    println()

    // 82_ @Label 로 반복문의 종료영역 선택하기
    for(n in 0..5){
        if(n == 3) break
        print("$n   ")
    }
    println()

    for(y in 0..5){
        print("$y : ")
        for(x in 0..10){
            if(n == 6) break
            print("$x   ")
        }
        println()
    }
    println()
    println()

    // 83_ @Label 로 break 의 위치를 선택할 수 있다!
    KKK@ for(y in 0..5){
        print("$y : ")
        JJJ@ for(x in 0..10){
            if(x == 3) {
                //break@JJJ
                break@KKK
            }
            print("$x   ")
        }
        println()
    }


}