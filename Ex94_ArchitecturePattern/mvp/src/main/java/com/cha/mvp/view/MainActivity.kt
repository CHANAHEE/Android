package com.cha.mvp.view
// 현업에서 사용하는 패키지 명은? 1_ 주석에서 Presenter 가 해야할 작업이 아니라 Model 아닌가?
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cha.mvp.R
import com.cha.mvp.databinding.ActivityMainBinding
import com.cha.mvp.model.Item
import com.cha.mvp.presenter.MainContract
import com.cha.mvp.presenter.MainPresenter

class MainActivity : AppCompatActivity(),MainContract.View {

    // 1_ MVP [ Model - View - Presenter ] - view 와 model 의 완전분리가 특징
    // 뷰와 Presenter 가 해야할 작업을 미리 인터페이스로 규격화를 한것이 특징 - 모듈화된 작업 템플릿을 만들 때 용이한 구조.

    // 2_ 1) Model : MVC 패턴의 모델과 같은 역할 [ 데이터를 취급 : Item 클래스, Person 클래스, ItemModel 클래스 등등.. ]
    // 3_ 2) View : 사용자가 볼 화면 및 이벤트 처리 [ activity_main.xml, MainActivity.kt, MyFragment.kt 등등.. ]
    // 4_ 3) Presenter : 뷰와 모델의 중계 역할. Controller 와 비슷하지만 인터페이스로 역할을 정해놓음.

    // 5_ 주요특징 : View 와 Presenter 가 해야할 작업을 미리 interface 를 통해 규격화 해놓기.

    // 24_ 바인딩 객체 만들기
    lateinit var binding: ActivityMainBinding
    lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 25_ # presenter 객체 생성 및 초기화
        presenter = MainPresenter()
        presenter.initial(this)

        // 26_ # view 로서의 역할
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener { presenter.clickSave(binding.etName.text.toString(),binding.etEmail.text.toString()) }
        binding.btnLoad.setOnClickListener { presenter.clickLoad() }
    }

    override fun showData(item: Item) {
        binding.tv.text = "${item.name} : ${item.email}"
    }

    override fun getContext(): Context {
        return this
    }

    // 27_ ## MVP 장점 ##
    // 1. MVC 처럼 데이터를 제어하는 코드가 Activity/ Fragment 클래스 안에 없어서 간결함.
    // 2. MVC 보다 조금 더 명확하게 각 역할별 코드가 잘 분리되어 작성.
    // 3. 각 역할이 인터페이스로 규격화되어서 유지보수나 인수인계가 용이함.
    // 4. view 안에서 model 을 참조하고 있지 않기에 model 의 변화에 영향받지 않는다.

    // 28_ ## MVP 단점 ##
    // 1. MVC 보다 만들어야 할 기본 파일들이 많아서 구조가 더 복잡해 보임.
    // 2. view 와 presenter 가 1:1 로 대응되어 파일들이 만들어짐. -> 화면하나 만들때마다 3개씩 만들어지므로 파일이 너무 많아진다.
    // 3. 규모가 커지면 결국 중간자의 역할을 하는 presenter 가 비대해짐.
}