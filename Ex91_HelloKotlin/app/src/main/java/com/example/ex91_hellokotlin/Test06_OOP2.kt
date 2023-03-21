package com.example.ex91_hellokotlin

fun main(){
    /*
    *
    *
    *       코틀린의 상속
    *
    *
    * */
    // 6_ 그래서 아래와 같이 First 의 멤버들을 사용할 수 있다.
    val obj:Second = Second()
    obj.a = 100
    obj.b = 200
    obj.show()


    // 9_ 업캐스팅과 다운캐스팅
    var f:First = Second()
    f.show() // 11_ 실제 참조하는 객체의 show 가 호출된다.
    // 10_ 업 캐스팅 : 이미 Second 에는 First 의 멤버들이 다들어가있으니, First 타입의 변수로 Second 객체의 참조가 가능하다
    // 작은 리모콘으로 큰 티비를 제어한다. 단 넷플릭스는 못봄. 기능이 없어서.

    var s:Second = f as Second // 12_ 다운캐스팅 : 형변환 연산자 as 를 사용하여 캐스팅한다. () 로 형변환하는 건 없음!!
    s.b = 500
    s.a = 200
    s.show()




    // 13_ 상속 마무리 연습

    // 15_ 객체생성
    val person = Person("sam",20)
    person.show()

    // 18_ 객체 생성
    val student = Student("robin",22,"android")
    student.show()

    // 21_ 객체 생성
    val professor = Professor("kim",45,"mobile optimization")
    professor.show()

    // 23_ 객체 생성
    val alba = AlbaStudent("hong",25,"ios","pc management")
    alba.show()
}



















// 2_ First 클래스를 상속하는 클래스 - 상속 문법이 extends -> : 으로 바뀜
// 4_ 상속하는 클래스 명 옆에 반드시 생성자 호출문이 명시되어야 함!!
class Second : First() {
    // 5_ First 의 멤버 a, show() 를 보유한 상태
    var b:Int = 20

    // 7_ 상속받은 show 메소드의 기능 개선 - override
    // 코틀린은 반드시 오버라이드임을 명시해야만 함
    override fun show(){
        super.show()
        println("b : $b")
    }
}


// 1_ 상속해줄 클래스 하나 만들자
// 3_ 근데 그냥 상속하려니 에러 -> 기본적으로 final class 로 지정되어있어, open 키워드가 있어야만 상속가능한 클래스를 만들수 있다.
open class First {
    var a:Int = 10

    // 8_ 기본이 final 메소드여서 override 안됨
    // open 키워드를 이용해 오버라이드 허용해주자.
    open fun show(){
        println("a : $a")
    }
}