package com.cha.ex101_coroutineandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1_ 코틀린 언어에서 지원하는 코루틴은 2종류 : GlobalScope, CoroutineScope
        // 안드로이드는 액티비티 or 프래그먼트의 라이프사이클이 존재함. 이에 함께 반응하는 코루틴이 존재. : LifecycleScope / ViewModelScope -- 별도의 라이브러리 추가가 필요!

        findViewById<Button>(R.id.btn1).setOnClickListener { clickBtn1() }
        findViewById<Button>(R.id.btn2).setOnClickListener { clickBtn2() }
        findViewById<Button>(R.id.btn3).setOnClickListener { clickBtn3() }

    }

    private fun clickBtn1(){
        CoroutineScope(Dispatchers.Default).launch {
            for(n in 0..20){
                Log.d("TAG123","코루틴 스코프 : $n")
                delay(500)
            }
        }
    }

    private fun clickBtn2(){
        // 2_ Android 의 라이프사이클에 같이 제어되는 코루틴 스코프 : LifecycleScope
        // 4_ this. 이 생략.. [ onCreate() ~~ onDestroy() 까지의 액티비티 라이프사이클 Owner를 이용한다 ]
        lifecycleScope.launch {
            for(n in 0..20){
                Log.d("TAG123","lifecycle scope : $n")
                delay(500)
            }
        }
    }

    private fun clickBtn3(){
        // 5_ onResume() 부터 onPause() 동안에만 코루틴은 동작한다. onPause() 만 되면 자동으로 일시정지 된다.
        // 다시 onResume() 되면 자동으로 이어서 실행된다.
        lifecycleScope.launchWhenResumed {
            loopTask()
        }
    }
//    override fun onBackPressed() {
//        finish()
//    }

    private suspend fun loopTask(){
        for(n in 0..20){
            Log.d("TAG123","lifecycle scope : $n")
            delay(500)
        }
    }
}