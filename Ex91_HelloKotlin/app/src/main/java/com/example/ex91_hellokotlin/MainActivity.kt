package com.example.ex91_hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// 1_ Kotlin 에서 클래스 상속 키워드는 ":" 이며, 상속하는 클래스명 옆에 주생성자를 호출하는 () 가 필수!
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2_ 대략적인 코틀린 코딩방식을 알아보자. - 자바와의 차이점을 기반으로 소개

        // 3_ 변수는 var 키워드를 사용!
        var btn:Button = findViewById(R.id.btn)

        // 4_ 버튼에게 클릭리스너 설정하기 - 자바의 람다식과 비슷한 SAM 변환 제공
        btn.setOnClickListener{
            clickBtn()
        }

    }

    // 5_ 코틀린에서의 메소드(함수) 는 fun 키워드를 사용!
    fun clickBtn(){
        // 6_ 변수의 타입은 생략이 가능! 코틀린에서 알아서 타입을 결정해준다!
        var tv = findViewById<TextView>(R.id.tv)

        // 7_ 코틀린은 setXXX(), getXXX() 등의 사용을 권장하지 않으며, 그 대신 멤버변수의 값 대입을 선호한다.
        tv.text = "Nice to meet you"
    }

    // 8_ override 메소드가 Java 에서는 @Override 어노테이션을 사용했지만,
    // 코틀린에서는 메소드 앞에 override 키워드가 삽입된다. 
    // 오버라이드 메소드앞에 명시적으로 override 키워드가 없으면 에러 
    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }
}