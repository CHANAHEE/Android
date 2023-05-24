package com.cha.mvvm.view

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cha.mvvm.R
import com.cha.mvvm.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.cha.mvvm.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    // 1_ MVVM 패턴 [ Model - View - ViewModel ] : view 와 model 의 데이터를 연결해(data binding) 놓아서 model 의 데이터가 변경될 때 별도의 처리코드 없이 view 가 자동 갱신되는 특징

    // 1) Model - 다른 패턴의 model 과 같음 [ Item, ItemModel ]
    // 2) View - 사용자가 볼 화면, 클릭이벤트 등을 처리하여 ViewModel 에게 model 제어를 요청 [ activity_main.xml, MainActivity.kt , Fragment.. ]
    // 3) ViewModel - 뷰와 모델을 연결하는 역할, view 가 연결 (binding) 한 데이터를 제어하도록 요청하는 코드가 있는 클래스

    // ** View 는 ViewModel 을 참조하고, ViewModel 은 Model 을 참조하고 있음.

    // 2_ MVVM 을 위해서는 [ dataBinding : 데이터바인딩 ] 기술을 이용하여 개발하는 것이 일반적임. -> dataBInding 추가하고 패키지 구분해놓음.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 3_ # view 역할
        // 레이아웃 xml 과 연결하는 바인딩 클래스 [ activity_main.xml --> ActivityMainBinding ]
        // 4_ 데이터바인딩은 뷰바인딩과 다르게 xml 파일의 root 요소가 <layout> 이어야 바인딩클래스가 만들어짐. -> activity_main.xml 에 만들러가자~

        // 7_ 바인딩 객체 만들기!
        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.vm = MyViewModel(this)

    }


}


// ## MVVM 장점
// 1. view 와 presenter 처럼 1:1 로 대응되어 있지 않아, 화면이 많아져도 ViewModel 은 재사용이 가능하다.
// 2. 사용자의 이벤트를 viewModel 이 모두 하고 있기에 화면이 바뀌어도 이벤트처리에 대한 중복코드가 필요 없다.
// 3. view 는 viewModel 을 참조하지만, viewModel 은 view 를 참조하지 않기에 view 가 변경되어도 뷰모델은 영향이 없음.
// 4. Activity 나 Fragment 의 코드가 가장 간결하다.

// ## MVVM 단점
// 1. MVVM 설계 학습이 어렵다.
// 2. view 처리가 많아지면 viewModel 의 코드가 많아져서 결국 비대해짐.