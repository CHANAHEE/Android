package com.example.ex92_kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ItemDetailActivity : AppCompatActivity() {

    val iv:ImageView by lazy { findViewById(R.id.iv) }
    // 26_ 참조변수의 자료형을 자동 추론시키면 find 할 때 제네릭을 이용해야한다.
    val tv by lazy { findViewById<TextView>(R.id.tv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        // 27_ 여기로 넘어온 Intent 객체가 가져온 Extra 데이터들을 받아보자.
        var name:String? = intent.getStringExtra("name")
        var msg:String = intent.getStringExtra("msg").toString()
        var imgId:Int = intent.getIntExtra("imgId",R.drawable.ic_launcher_background)

        supportActionBar?.title = name
        tv.text = msg
        Glide.with(this).load(imgId).into(iv)

        // 32_ 별칭 지정
        iv.transitionName = "iii"
    }
}