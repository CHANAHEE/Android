package com.cha.ex94_architecture_pattern

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cha.ex94_architecture_pattern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1_ Architecture pattern 없이 그냥 작성해보자. 필요성을 느끼자. - Flat Design 방식
    // 4_   1) 장점 : 구조가 간단하여 구현하기가 쉽다. 하나의 문서에 대부분의 기능코드와 UI 코드가 있어서 전체 기능이 한눈에 들어온다.
    //      2) 단점 : Activity, Fragment 에 모든 기능 코드가 있어, 규모가 커지면 단일 파일안에 너무 많은 코드가 있어 비대해져 유지보수가 어렵다.
    //               똑같은 데이터를 제어하는 코드를 다른 화면에서 사용하게 되더라도 같은 코드를 또 작성해야함. 재사용이 어렵다.

    // 5_ 그래서 등장한 개념이 Architecture Pattern !!
    // 6_ 작성 코드의 " 역할 " 에 따라 구분하여 작성하는 방법을 규격화된 패턴
    // 7_ 대표적인 패턴 : MVC , MVP, MVVM - 차례대로 실습.. 새로운 모듈을 만들어서 실습해보자~



    // 2_ 테스트용 xml 만들자~~ 뷰바인딩도~~

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // # 3_ 화면작업을 위한 코드
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
        // # 3_ 클릭 이벤트에 대한 처리 코드
        binding.btnSave.setOnClickListener { clickSave() }
        binding.btnSave.setOnClickListener { clickLoad() }
    }

    // # 3_ Data 를 제어 ( 저장/ 읽기/ 삭제/ 변경 : CRUD ) 작업을 하는 비지니스 로직 코드 기능 메소드 2개 [ ex.. 네트워크 통신, DB 작업 등 ]
    private fun clickSave(){
        val pref: SharedPreferences = getSharedPreferences("data.xml", MODE_PRIVATE)
        pref.edit().apply {
            putString("name",binding.etName.text.toString())
            putString("email",binding.etEmail.text.toString())
            commit()
        }
    }

    private fun clickLoad(){
        val pref: SharedPreferences = getSharedPreferences("data.xml", MODE_PRIVATE)
        var name = pref.getString("name","none")
        var email = pref.getString("email","none")

        binding.tv.text = "$name : $email"
    }
}