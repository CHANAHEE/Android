package com.example.ex92_kotlinrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

class IntroActivity : AppCompatActivity() {

    // 1_ 코틀린은 프로퍼티(멤버변수)를 초기화하지 않으면 에러!
    var btn:Button? = null
    lateinit var btn2:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        btn = findViewById(R.id.btn)

        // 2_ Nullable 변수는 null 일수도 있어서 안전하게 멤버에 접근하는 연산자를 사용해야함 : Null safe 연산자
        btn?.setOnClickListener(object : OnClickListener{
            override fun onClick(p0: View?) {
                val intent:Intent = Intent(this@IntroActivity,MainActivity::class.java)
                startActivity(intent)
            }
        })

        // 3_ btn2 참조하기
        btn2 = findViewById(R.id.btn_exit)
        // 4_ 리스너 설정을 익명클래스 말고 간결하게 람다로 해보자.
        // btn2.setOnClickListener({ v-> finish() })
        // 5_ 파라미터가 1개라면 생략가능 : 자동 it 키워드로 파라미터명이 지정됨.
        // btn2.setOnClickListener({ finish() })
        // 6_ 더 줄였음 : SAM (Single Abstract Method) 변환
        btn2.setOnClickListener { finish() }

        // 7_ MainActivity 로~
    }
}