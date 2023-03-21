package com.example.ex91_hellokotlin


fun main(){
    /*
    *
    *
    *       코틀린의 OOP
    *
    *
    * */

    // 5_ 객체 생성 -> new 키워드가 없음!
    var obj:MyClass = MyClass()
    obj.show()

    // 6_ 별도의 파일로 클래스 생성도 가능! 만들어보자!
    var obj2:MyKotlinClass = MyKotlinClass()
    obj2.show()

    // 10_ 생성자 만들기
    // 11_ 코틀린의 생성자는  2가지 종류가 있음. [주생성자, 보조생성자]

    // 12_ Primary Constructor
    var s = Sample() // 14_ 주 생성자 자동호출

    // 17_ 주생성자는 오버로딩 불가!

    // 19_ 객체 생성
    var s2 = Sample2(100,200,300)
    s2.show()

    // 28_ 객체 생성
    val s3 = Sample3(100)


    // 31_ 객체 생성
    val s4 = Sample4()
    val s5 = Sample4(100)

    // 36_ 객체 생성
    Sample5()
}

// 35_ 주생성자의 constructor 키워드 생략가능
class Sample5(){
    init{
        println("Sample5 primary constructor")
    }
}









// 30_ primary 와 secondary 를 같이 써보자! 그럼 각각의 장점을 뽑아낼수 있겠네!
class Sample4 constructor(){
    init {
        println("Sample4 Primary Constructor")
    }
    // 32_ 오버로딩의 역할을 하는 보조생성자 만들기
    // 33_ 주생성자와 함께 사용할 때는 반드시!! 보조 생성자에서 주 생성자의 호출문이 명시적으로 들어가야함.
    // 34_ 근데 아래와 같이 this 를 사용하여 호출문을 작성해주어야 한다!
    constructor(num: Int):this(){
        println("Sample4 의 오버로딩 constructor : $num")
    }
}






// 26_ 보조생성자 - 자바처럼 class 안에 메소드처럼 존재하는 생성자
class Sample3 {
    // 27_ 보조생성자 만들기
    constructor(){
        println("Sample3 의 보조 생성자")
    }
    // 29_ 보조생성자 오버로딩 가능! - 애석하게 var, val 키워드는 사용불가!
    constructor(num:Int){
        println("Sample3 의 보조 생성자 : $num")
    }
}









// 18_ 주생성자에 파라미터를 전달하는 클래스를 새로 만들어보자.
// 21_ 이제 파라미터를 하나 더 추가해보자

// 25_ 주생성자의 파라미터에 var , val 키워드가 있으면.. 파라미터면서 멤버변수!! 귀찮은 작업 없이 바로 변수생성이 가능~ 클래스 전체에서 사용가능하다.
// 원래 파라미터에 var, val 키워드는 못쓴다! 근데 주생성자에만 특별하게 사용가능!
class Sample2 constructor(num:Int, num2:Int, var num3:Int){
    // 23_ 멤버변수를 만들자
    var num2:Int = 0

    init{
        // 20_ 사실 좀 이상하지. 중괄호 안에 중괄호에서 생성자의 파라미터를 건드리니까.
        // 근데 이건 그냥 내부적으로 사용가능하게끔 해준것같음
        println("Sample2 : $num")
        println("Sample2 : $num2")
        println("Sample2 : $num3")
        // 24_ 초기화 작업! 근데 이러한 코드들은 보일러플레이트 코드! 그래서 코틀린에서는 좀 더 편한 방법을 제공한다.
        this.num2 = num2
    }

    fun show(){
        // 22_ num2 를 다른 지역에서 사용하고 싶다면 멤버변수로 만들어야 함.
        println("num2 : $num2")
        println("num3 : $num3")
    }
}





// 13_ 주생성자 - 클래스 이름 옆에 작성
// 15_ 주생성자는 별도의 {} 가 없음
class Sample constructor(){



    // 16_ 초기화 블럭 : 주생성자의 내용을 작성할 수 있는 영역 - init {}
    init {
        println("Sample 클래스의 Primary Constructor")
        println()
    }
}








// 1_ 클래스 선언
class MyClass{
    // 2_ 멤버변수 - property : 프로퍼티
    // 3_ 반드시 초기화 해야함
    var a:Int = 10

    // 4_ 멤버함수 - method : 메서드
    fun show(){
        println("show : ${a}")
    }
}