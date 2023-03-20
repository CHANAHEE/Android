package com.example.ex91_hellokotlin

// 9_ 문법적 주요 특징
// 1. 문장의 끝을 나타내는 ; 을 사용하지 않음. 써도 에러는 아니지만, 코틀린 스타일은 아님.
// 2. 변수를 만들때, 자료형을 먼저 쓰지 않고 var, val 키워드를 사용한다. 단, 자료형은 존재한다. 또한 자동형변환도 없음.
// 즉, 정적 타입의 언어임.
// 3. new 키워드 없이 객체를 생성함. new String() --> String()
// 4. 안전하게 null 을 다룰 수 있는 문법이 제공됨.
// 5. 코틀린은 함수형 언어! 즉, 함수를 객체처럼 변수에 저장하고 파라미터로 넘겨주는 등의 작업이 가능함.

// 10_ 프로그램의 시작함수가 있다!! main 함수가 반드시 있어야함! 지금은 안드로이드가 아니니까!
fun main(){
    // 11_ 화면(콘솔창-run) 출력 : print()

    /*
    *   화면 출력 함수
    */
    // 12_ 화면 출력 : print(),println() 함수
    print(10)
    print(3.14)
    print("Nice")

    // 13_ print() 는 자동줄바꿈 안됨, 자동 줄바꿈하는 기능함수 println()
    println()
    println("Hello Kotlin")
    println(10)
    println(10.24)
    println('A')
    println(true)
    println(5+3)

    // 14_ 변수명을 전달하면 변수 안에 값이 출력
    var a:Int = 10
    println(a)
    var b:String = "Hello"
    println(b)

    /*
    *
    *
    *  자료형과 변수
    *
    *
    */
    // 15_ 기초타입 : Boolean, Byte, Char, Short, Int, Long, Float, Double
    // 기본적으로 코틀린은 모든 변수가 참조형이라고 보는게 맞다.

    // 16_ 참조타입 : String, Random, Any(Java 의 Object와 비슷 -> 최상위) , Unit... 그 외 Kotlin APIs, Java APIs...

    // 17_ 변수의 두가지 종류 : var, val [문법 -- var 변수명:자료형, val 명수명:자료형]
    // 1. var [ 값 변경이 가능한 변수 ]
    var num:Int = 10
    println(num)

    var num2:Double = 3.14
    println(num2)

    // 18_ 권장하진 않지만 변수를 만들때 값을 초기화하지 않아도 된다.
    var num3:Float
    num3 = 5.23f
    println(num3)

    // 19_ 변수이므로 변수가 가지고 있는 값의 변경가능
    num = 20
    num2 = 20.5
    num3 = 11.4f
    println(num)
    println(num2)
    println(num3)

    // 20_ 자료형이 정해진 변수이므로, 다른 자료형의 대입은 ERROR!!
//    num = 3.14 // ERROR [Int 변수에 Double 대입]
//    num = 50 // ERROR [Double 변수에 Int 대입]

    // 21_ 명시적으로 형변환 하여 값 대입해보기
    // [.toXXX() 로 변환 가능(기초 타입만 가능!)]
    num = 3.14.toInt()
    num2 = 50.toDouble()
    println(num)
    println(num2)

    // 22_ 문자열 String 객체
    var s:String = "Hello"
    println(s)

    // 23_ var s2:String = String("Hello") // error - 단순 문자열을 생성할 때, 생성자 사용 불가
    // [String() 생성자는 Buffer 나 Byte 배열을 String 객체로 생성할 때만 사용]



    // 2. val [값 변경이 불가능한 변수 - 읽기전용] 상수는 const val 키워드를 사용
    val n1:Int = 100
    println(n1)

    val n2:Boolean = true
    // n2 = false // error
    println(n2)

    // 24_ 권장하지 않지만 지역 변수 선언할 때 초기화 안해도 되는 특징은 val 도 마찬가지 임.
    val n3:String
    n3 = "Nice" // 이때 값이 정해짐
    //n3 = "Good" // error
    // 25_ var, val 사용팁
    // 데이터를 가지고 있는 변수 var [ ex : name,title,age ... ]
    // 객체를 참조하는 변수 val [ ex : TextView, ImageView ... ]

    // 26_ 자료형을 생략하며 변수 선언 가능함 - 자료형은 자동 추론됨
    var aa = 10
    println(aa)

    var bb = 3.14
    println(bb)

    var cc = 3.14f
    println(cc)

    var dd = true
    println(dd)

    var ee = 'A'
    println(ee)

    var ff = "Hello"
    println(ff)
    // 27_ 변수 선언시 변수 자료형 표기가 없지만 값을 대입하면서 자료형이 자동 추론된것임.
    // 즉, 변수는 자료형이 있는것! 없는게 아니다!

    // 28_ 자료형 명시 생략을 통해 자동 추론하려면
    // 변수 선언과 함께 반드시 값을 대입해야함.
    // var gg // error!
    // gg = 10

    // 29_ 정수값 표기의 특이한 점 [실생활에서 숫자의 3자리 마다 , 찍는거]
    var a3 = 5_000_000
    println(a3) // 30_ 출력은 구분자 없이...

    // 4. 코틀린만의 자료형 타입
    // Any 타입 [자바의 Object 처럼 최상위 타입 ]
    // 최상위 타입은 어떤 객체는 참조가 가능하다
    var v:Any
    v = 10
    println(v)

    v = 3.14
    println(v)

    v = "Hello"
    println(v)
    // 31_ 편해보이지만, 실제 개발시 어떤 자료형인지 예측하긱 어려워 꼭 필요할 때만 사용해야함.

    // 5. null 값에 대한 자료형 - [null 안정성: 별도로 추가 수업 예정  - 여기서는 대략적인 특징만]
    // 32_ 코틀린은 쟈료형을 명시하면 null 값을 저장할 수 없음.
    // var nn:Int = null // error
    // var ss:String = null // error
    // 기본적으로 null을 저장할 수 없음

    // 33_ null 값을 가질 수 있는 변수라고 표시할 수 있음 - Nullable 변수
    var nn:Int? = null
    var ss:String? = null
    println(nn)
    println(ss)
    println()

    // 34_ Nullable 변수 사용의 특이점
    var sss:String = "Hello"
    println( sss.length )

var ssss:String? = null
//println( ssss.length ) // 35_ Nullable 변수는 그냥 . 으로 멤버사용 불가
println( ssss?.length ) // 36_ ?. 연산자 : null 이 아닐 때만 length 를 출력함
println()

    ///////////////////////////////////////////////

    // 37_ 화면 출력의 Format 만들기
    // 38_ 문자열 결합에 대한 내용
    println("Hello " + "Kotlin")

    // 39_ 숫자 타입과 String 타입은 자동 결합이 안될 수 있음!
    // println(10+"Hello") // error!
    // 40_ 그래서 number 타입을 string 으로 변환하여 결합해주어야 함!
    println( 10.toString() + "Hello")

    // 41_ 특이점 : 문자열이 먼저 있으면 결합됨.
    println("Hello" + 10)
    println()

    // 42_ 변수 2개의 값을 덧셈하여 출력하는 코드를 짜보자.
    var nnn1:Int = 50
    var nnn2:Int = 100
    // 43_ "50+30 = 80" 모양으로 포멧팅하여 출력
    // println(nnn1.toString() + " + " + nnn2 + " = " + (nnn1 + nnn2))
    // println("" + nnn1 + " + " + nnn2 + " = " + (nnn1 + nnn2))

    // 44_ 위 방법 모두 짜증. 결합연산으로 포맷팅하면 코드가 지저분.
    println(" ${nnn1}원 + $nnn2 = ${nnn1 + nnn2}")
    // 45_ 이렇게 문자열 안에 $ 변수명을 사용하는 것을 [문자열 템플릿] 이라고 부름

    /////////////////////////////////////////////////////////////////

}