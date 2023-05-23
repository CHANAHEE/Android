package com.cha.mvc.controller

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cha.mvc.R
import com.cha.mvc.databinding.ActivityMainBinding
import com.cha.mvc.model.ItemModel

class MainActivity : AppCompatActivity() {

    // 8_ 1) MVC [ Model - View - Controller ] - 각 파일의 역할을 구분하여 작성하는 것이 특징
            // 1) Model - 데이터를 저장하는 클래스 혹은 DB/ 네트워크/ 파일 등에서 데이터를 불러오거나 저장하는 등의 작업을 하는 코드가 작성된 파일들
            // [ ex.. Item 클래스, Person 클래스, Retrofit 작업클래스, DB 작업 클래스.. ]

            // 2) View - 사용자가 볼 화면을 구현하는 목적의 코드가 있는 파일들
            // [ ex.. activity_main.xml, MainActivity.kt, fragment_my.xml 등등 ]

            // 3) Controller - 뷰와 모델 사이에서 연걸하는 역할, 클릭 같은 이벤트를 처리하여 뷰의 요청에 따라 model 데이터를 제어하여 뷰에게 보여주는 역할.
            // [ Activity.kt, Fragment.kt ] ( 이 둘은 View 의 역할이기도 함 )

    // 9_ app 모듈에서 만든 Flat Design 에서 MainActivity.kt 에서 작성한 코드들을 크게 3가지 역할로 구분
    // #1 화면구현                                                                         -- View 역할
    // #2 클릭 이벤트 처리                                                                  -- Controller 역할
    // #3 SharedPreferences 를 이용하여 데이터를 저장하고 불러오는 Business Logic 을 가진 기능    -- Model 역할

    // 10_ 역할별 파일에 대한 구분을 쉽게 하기 위해 java 폴더 안에 파일의 역할별로 package 로 나누기도 함.


    // 11_ 중략. Item 데이터 클래스를 만들자.
    // 16_ 이제 참조변수 만들자
    // # view 참조변수
    lateinit var binding: ActivityMainBinding

    // # model 참조변수
    lateinit var model: ItemModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // # view 역할
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // # model 객체 생성
        model = ItemModel(this)

        // # controller 역할 - 클릭 이벤트 처리 [ view 와 model 연결작업 수행 ]
        binding.btnSave.setOnClickListener { model.saveData(binding.etName.text.toString(),binding.etEmail.text.toString()) }
        binding.btnLoad.setOnClickListener { binding.tv.text = "${model.loadData().name} : ${model.loadData().email}" }
    }
}

// 17_ ## MVC 의 장점 ##
// 1) 데이터를 제어하는 코드가 Activity 나 Fragment 에 있지 않아서 간결해짐
// 2) 역할별로 코드가 분리되어 있어서 가독성이 좋다. 또한 기능 코드 위치를 식별하여 찾기 수월 -> 유지보수가 용이
// 3) model 역할을 하는 클래스 (Item) 안에 어떤 View 도 참조하고 있지 않다. 그래서 view 를 변경해도 model 의 코드는 전혀 변경되지 않는다.
// 그래서 다른 뷰에서도 재사용이 가능하다.

// 18_ ## MVC 의 단점 ##
// 1) Android 에서는 View 와 Controller 의 역할 분리가 어렵다. Activity 는 View 이면서 Controller. 이건 어쩔수가 없다.
// 2) view 에서 model 객체를 참조하고 있음. 그래서 model 이 바뀌면, view 도 코드 변경이 필요함.
// 3) 규모가 커지면 controller 역할을 하는 Activity 코드는 여전히 비대해짐.