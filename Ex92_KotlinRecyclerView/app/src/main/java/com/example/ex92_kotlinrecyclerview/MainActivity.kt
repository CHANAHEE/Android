package com.example.ex92_kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Recycler

class MainActivity : AppCompatActivity() {

    // 8_ 뷰 참조변수 : 보통 참조값이 변경되지 않으므로 var 보다는 val 로 만들기
    // 9_ 늦은 초기화하기
    val recycler:RecyclerView by lazy { findViewById(R.id.recycler) }

    // 12_ 대량의 데이터 만들자
    var items:MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 13_ 대량의 데이터 추가[테스트 목적임]
        items.add(Item("sam","Hello kotlin",R.drawable.bg_one08))
        items.add(Item("robin","Hello Android",R.drawable.bg_one09))
        items.add(Item("tom","Nice to meet you",R.drawable.bg_one10))
        items.add(Item("rob","Hello kotlin",R.drawable.bg_one08))
        items.add(Item("him","Hello Android",R.drawable.bg_one09))
        items.add(Item("tomas","Nice to meet you",R.drawable.bg_one10))
        items.add(Item("shit","Hello kotlin",R.drawable.bg_one08))
        items.add(Item("chang","Hello Android",R.drawable.bg_one09))
        items.add(Item("hoon","Nice to meet you",R.drawable.bg_one10))
        // 14_ 이제 아이템 시안만들러가자

        // 19_ 리사이클러뷰에 어댑터를 설정하기
        recycler.adapter = MyAdapter(this,items)

        // 20_ 리사이클러뷰의 레이아웃 매니저를 설정하기
        recycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }


    override fun onResume() {
        super.onResume()
        // 21_ 보통 이곳에서 데이터들을 갱신하는 작업들이 이루어진다.
        recycler.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 22_ 옵션메뉴 만들기 : MenuInflater 를 get 하는 작업 필요없이 액티비티에 이미 menuInflater
        // 객체가 멤버로 존재한다.
        menuInflater.inflate(R.menu.option,menu)

        return super.onCreateOptionsMenu(menu)
    }

    // 23_ 옵션메뉴 아이템 클릭 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_aa-> Toast.makeText(this, "aa 옵션메뉴", Toast.LENGTH_SHORT).show()
            R.id.menu_bb-> {
                Toast.makeText(this, "bb 옵션메뉴", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}