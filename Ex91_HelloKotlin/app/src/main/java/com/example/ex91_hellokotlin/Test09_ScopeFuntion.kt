package com.example.ex91_hellokotlin

fun main(){
    // 1_ scope function : apply, let, run, also

    // 2_ 어떤 객체의 여러개의 멤버를 사용해야 할 때
    val member:Member = Member()
    member.name = "sam"
    member.age = 25
    member.address = "seoul"
    member.show()

    // 3_ 멤버 4개를 사용할 때 객체명.xxx 식으로 쓰는게 은근 번거롭다~ 실수의 여지도 많다.

    // 4_ 이를 위해 등장한 scope 함수
    val member2 = Member()
    member2.apply {
        // 5_ 이 영역 안에서는 this 키워드가 member2 객체임
        this.name = "robin"
        age = 25
        address = "busan"
        show()
    }
    // 6_ 위처럼 영역을 묶었기에 참조변수 명을 잘못 기입하는 실수를 줄일 수도 있고 개발자가 볼 때
    // member2 에 대한 설정을 하나의 영역에 묶어서 가독성이 개선됨

    // 7_ scope function 의 크게 2가지 분류
    // 8_ 영역 안에서 this 키워드로 본인을 참조하는 scope function : apply, run
    // 9_ 영역 안에서 it 키워드로 본인을 참조하는 scope function : also, let -- 마치 람다식 처럼 사용
    val member3:Member = Member()
    member3.also {
        it.name = "hong"
        // 10_ it 키워드는 생략 불가.
        it.age = 30
        it.address = "seoul"
        it.show()
    }

    // 11_ it 키워드는 다른 키워드로 변경 가능
    var result = member3.let {
        m->
        m.name
        m.age
        m.address = "incheon"
    }
    println(result)
}

class Member {
    var name:String? = null
    var age:Int? = null
    var address:String? = null

    fun show(){
        println("$name  $age  $address")
    }
}