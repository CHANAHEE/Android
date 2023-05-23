package com.cha.mvp.presenter
// 왜 model 은 인터페이스 규격화를 하지 않을까?

import com.cha.mvp.model.ItemModel
import com.cha.mvp.view.MainActivity

// 15_ presenter 라면 가져야할 기능을 기술한 인터페이스를 구현하여 실제 기능을 작성하는 클래스
class MainPresenter: MainContract.Presenter {

    // 16_ presenter 는 view 와 model 을 연결해야 하기에 각각의 참조변수를 멤버로 보유
    var view: MainContract.View? = null // 17_ view 역할을 수행하는 클래스는 MainContract.View 인터페이스를 구현하고 있기에
                                        // 특정 Activity/Fragment 를 직접 자료형으로 참조하고 있지 않음. -> 약한 결합도
    var model: ItemModel? = null // 18_ model 역할을 수행하는 클래스 참조변수

    // 19_ presenter 가 연결한 2개의 참조변수를 생성 및 전달받는 메소드 정의
    fun initial(view: MainContract.View){
        this.view = view
        this.model = ItemModel(view.getContext())
    }

    // 20_ View 의 save 버튼 클릭 이벤트를 대신 처리해주는 기능 메소드
    override fun clickSave(name: String, email: String) {
        model?.saveData(name,email)
    }

    override fun clickLoad() {
        // 21_ model 에게 date 를 요청
        var item = model?.loadData()
        // view?.showData(item!!)

        // 22_ view 에게 데이터 출력 요청
        item?.let{
            view?.showData(it)
        }

        // 23_ 이제 뷰 단의 처리를 하러가보자!
    }
}