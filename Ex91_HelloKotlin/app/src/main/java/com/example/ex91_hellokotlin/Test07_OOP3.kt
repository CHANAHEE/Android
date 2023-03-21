package com.example.ex91_hellokotlin

fun main(){


    /*
    *
    *
    *        안드로이드에서 많이 사용하는 기술
    *           1. 이너클래스
    *           2. 인터페이스 및 익명클래스
    *           3. static 키워드를 대체한 companion object[동반객체]
    *           4. 늦은 초기화 - lateinit, by lazy
    *
    *
    * */
    // 1_ 이너클래스
    val obj = AAA()

    // 4_ inner class 는 외부에서 직접 객체생성 불가!
    // 5_ inner class  객체는 아우터클래스만 만들 수 있음.
    val obj2:AAA.BBB = obj.getBBBInstance()
    obj2.show()

    // 9_ 인터페이스는 기능 설계가 안되어있기 때문에 객체생성 불가.
    // 13_ 인터페이스를 구현한 Test 클래스를 객체로 생성하자
    val c:Clickable = Test() // 14_ up casting : 개발자가 Clickable 을 구현해야한다는 사실을 강조하기 위해 업캐스팅 사용
    c.onClick()
    println()

    // 15_ 익명클래스
    // 다른 기능을 하는 또 다른 Clickable 이 필요
    // 근데 그러려면 Test 같은 또 다른 클래스를 만들어서 사용해야함. ~ 짜증이난다

    // 16_ 그래서 객체 생성하면서 인터페이스를 그 자리에서 구현하는 이름없는 클래스를 사용 - 익명클래스
    val c2:Clickable = object : Clickable {
        override fun onClick() {
            println("apple...")
        }
    }
    c2.onClick()
    println()


    // 16_ 정적멤버 static 키워드의 대체문법 : 동반 객체 (Companion object)
    // 클래스에 붙어 있는 객체같은 녀석
    // 별도의 객체생성없이 클래스명만으로 접근 가능한 녀석
    println(Simple.title)
    Simple.show()



    // 19_ 늦은 초기화
    // 20_ lateinit : var 변수만 사용가능
    var h:Hello = Hello()
    h.name = "robin"
    h.onCreate()
    println(h.name)

    // by lazy : val 변수만 사용가능
    println(h.add) // 29_ add 변수는 객체 생성시 초기화
    println(h.address) // 30_ address 변수는 이 실행문이 실행될 때 초기화


}

class Hello {
    // 21_ lateinit 을 알아보자.
    // 22_ 만약 초기화를 나중에 하고 싶다면..
    lateinit var name:String

    fun onCreate(){
        name = "sam"
    }

    // 23_ lateinit 사용시 특징
    // 24_ Nullable 변수는 lateinit 불가
    // lateinit var title:String? // ERROR! 그냥 null 넣어~
    // 25_ 기본자료형은 사용불가
    // lateinit var age:Int // ERROR! 그냥 0 같은 값 넣어~
    // 26_ val 에는 사용불가.

    // 27_ by lazy 사용시 특징
    // val address:String // ERROR! 초기화 해줘야함.
    // 28_ 이 변수가 처음 사용될 때 초기화된다!
    val address:String by lazy { "seoul" }
    val add:String = "busan"

    // 31_ 기본형 자료형도 가능함
    val age:Int by lazy { 20 }

    // 32_ Nullable 도 가능함
    val message:String by lazy{"Hello"}
    val message2:String? by lazy{null}

    // 33_ 조건값으로 값 대입도 가능함
    val message3:String by lazy {
        if(age<20) "미성년자"
        else "성인"
    }

    // 34_ var 에는 사용불가
}








class Simple {
    var a:Int = 10 // 17_ 인스턴스 변수는 객체 생성했을 때만 사용이 가능!
    companion object{
        // 18_ 이 안에 있는 멤버들은 이미 객체화 되어있기 때문에 객체 생성없이 사용 가능!
        // 단, Simple 클래스에 동반되었기 때문에 클래스명이 요구됨.
        // 근데 static 을 없앤 이유는 언급이 없지만, 유추하건데, 필요성이 없기 때문. 왜? 전역변수가 있으니까.
        var title:String = "Hello"
        fun show(){
            println("동반객체의 show()")
        }
    }
}


// 10_ 인터페이스의 추상메소드들을 구현하는 별도의 클래스를 설계하고
// 이 클래스를 객체로 만들어서 사용해야만 한다.

// 11_ implements 대신 상속과 마찬가지로 : 기호를 사용한다.
// 12_ 인터페이스를 구현할 때는 상속과 다르게 인터페이스이름 옆에 () 생성자 호출문이 없다.
class Test : Clickable {
    override fun onClick() {
        println("clicked")
    }

}



// 8_ 인터페이스는 특별할건 없음 - 추상메소드만 가진 class
interface Clickable{
    // abstract method
    abstract fun onClick()
}














class AAA {
    var a:Int = 0

    fun show(){
        println("AAA 클래스의 show : $a")
    }

    // 6_ inner class 객체를 생성하여 리턴해주는 메소드
    fun getBBBInstance():BBB{
        return BBB()
    }
    // 3_ inner 키워드가 있어야 진정한 이너클래스가 완성됨
    inner class BBB{
        fun show(){
            // 2_ 이너클래스의 장점 - 아우터 클래스의 멤버를 제것인양..
            a = 100

            // 7_ 아우터의 show 메소드 호출해보기
            this@AAA.show()
        }
    }
}