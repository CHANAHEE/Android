package com.cha.ex97_databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.databinding.DataBindingUtil
import com.cha.ex97_databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 1_ 데이터바인딩에서는 뷰바인딩과 다르게 레이아웃 xml 파일의 root 요소가 <layout> 이어야만 바인딩 클래스가 만들어짐! -> xml 로~

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // 6_ 레이아웃 xml 에서 <data> 요소 안에 있는 <variable> 의 타입으로 지정한 User 클래스를 객체로 생성하여 값을 지정
        binding.user = User("sam",25)


    }
}