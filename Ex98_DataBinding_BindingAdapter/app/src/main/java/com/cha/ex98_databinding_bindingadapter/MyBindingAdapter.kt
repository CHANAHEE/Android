package com.cha.ex98_databinding_bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


// 9_ 기존 뷰들에 없는 새로운 xml 속성을 연결하는 기능 메소드를 가지는 객체 [ 보통 static 메소드를 가진 class로 사용 ]

// 13_ object 키워드 - 싱글톤 패턴으로 객체를 만들어주는 키워드
object MyBindingAdapter {

    // 10_ (1) 이미지뷰에 새로운 xml 속성을 만들기 - [ 속성명 : imageUrl ]
    @BindingAdapter("imageUrl") // 11_ 새로운 속성 만드는 어노테이션
    @JvmStatic
    fun bindUrlData(view: ImageView, url: String){ // 12_ 메소드명은 임의로 작성, 첫번째 파라미터는 목표 뷰, 두번째 파라미터는 데이터!
        Glide.with(view.context).load(url).into(view)
    }

    // 22_ (2) 리사이클러뷰에 리스트를 설정할 수 있는 새로운 xml 속성을 만들기 - [ 속성명 : itemList ]
    @BindingAdapter("itemList")
    @JvmStatic
    fun bindItemData(view: RecyclerView, item: Any){ // 23_ 컬렉션 타입은 사용불가능. Any 로 받자.
        view.adapter = RecyclerItemAdapter(item as MutableList<Item>)
    }
}