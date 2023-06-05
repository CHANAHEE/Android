package com.cha.ex100_coroutinetest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cha.ex100_coroutinetest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1_ Coroutine [코루틴] - 경량 스레드 : 스레드를 멈추지 않고 비동기 처리 (하나의 스레드 안에 여러개의 코루틴 실행)
        // 스레드가 요리사라면 멀티 스레드는 여러 요리사가 화구 (CPU) 를 번갈아서 쓰는것. 다른 요리사가 사용중이라면 기존 요리사는 동작을 멈춤.
        // 그에 반면에 코루틴은 하나의 요리사(스레드)가 파스타를 만들면서 스테이크까지 굽는 형식. 즉 후라이팬이 2개인 개념.
        // 자리를 비켜가며 멈추는 행동이 없어서 속도가 빠르다.

        // 2_ 코루틴을 구동하는 2개의 범위 (Scope) 가 존재함.
        // (1) GlobalScope      : 앱 전체의 생명주기와 함께 관리되는 범위
        // (2) CoroutineScope   : 버튼 클릭같은 특정 이벤트 순간에 해야할 Job 을 위해 실행되는 범위 [ ex. network 통신, DB CRUD , 특정 연산 수행 ]

        // 3_ 첫번째 실습 : GlobalScope 코드 연습
        binding.btn.setOnClickListener {
            // 4_ 코루틴 없이 오래걸리는 작업 실행해보기
//            for( n in 0 until 10){
//                Log.i("TAG123","$n")
//                Thread.sleep(500)
//            }

            // 5_ 비동기 작업으로 위 작업을 수행 - 코루틴을 사용해보기
            GlobalScope.launch {
                for( n in 0 until 10){
                    Log.i("TAG123","$n - ${Thread.currentThread()}")
                    delay(500) // 6_ 코루틴 내부에서 사용가능한 함수
                }
            }

            Toast.makeText(this, "헬롱", Toast.LENGTH_SHORT).show()
        }


        // 7_ CoroutineScope 비동기 실행
        // CoroutineScope 는 GlobalScope 와 다르게 해당 작업을 어떤 스레드에게 보낼지 결정하는 Dispatcher[디스패처] 를 지정해야함.
        // 8_ Dispatcher 의 종류
        // (1) Dispatchers.Default      - 기본 스레드풀의 스레드를 사용. [ cpu 작업이 많은 연산작업에 적합 ]
        // (2) Dispatchers.IO           - DB 나 네트워크 IO 스레드를 사용 [ 파일 입출력, 서버 작업에 적합 ]
        // (3) Dispatchers.Main         - Main 스레드를 사용 [ UI 작업 등에 적합 ]
        // (4) Dispatchers.Unconfined   - 조금 특별한 디스패처 [ 해당 코루틴을 호출하는 스레드에서 실행 ]
        binding.btn2.setOnClickListener {
            // 9_ Dispatchers.Default 사용
            CoroutineScope(Dispatchers.Default).launch {
                for( n in 100 downTo 0){
                    Log.i("TAG123","$n - ${Thread.currentThread().name}")

                    binding.tv.text = "$n"
                    delay(500)
                }
            }
            Toast.makeText(this, "헬롱", Toast.LENGTH_SHORT).show()
        }

        binding.btn3.setOnClickListener {
            // 10_ Main 에서 서버작업 시도.
            CoroutineScope(Dispatchers.Main).launch {
                // 11_ 네트워크 이미지 불러오기 -- ERROR! : MainThread 는 네트워크 작업 불가능
                val url = URL("https://cdn.pixabay.com/photo/2023/06/01/06/22/british-shorthair-8032816_640.jpg")
                val bm: Bitmap = BitmapFactory.decodeStream( url.openStream() )
                binding.iv.setImageBitmap(bm)
            }
        }

        binding.btn4.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val url = URL("https://cdn.pixabay.com/photo/2023/06/01/06/22/british-shorthair-8032816_640.jpg")
                val bm: Bitmap = BitmapFactory.decodeStream( url.openStream() )
                //binding.iv.setImageBitmap(bm) // 12_ UI 변경. 때문에 에러!
                withContext(Dispatchers.Main){
                    binding.iv.setImageBitmap(bm)
                }
            }
        }

        binding.btn5.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {

                // 13_ 작업 1
                launch {
                    for(n1 in 100 until 1001){
                        Log.d("TAG321","n1 : $n1")
                        delay(500)
                    }
                }

                // 14_ 작업 2
                launch {
                    for(n1 in 2000 until 3001){
                        Log.d("TAG321","n1 : $n1")
                        delay(500)
                    }
                }
            }
        }

        binding.btn6.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {

                // 15_ 작업 1
                launch {
                    for(n1 in 100 until 111){
                        Log.d("TAG321","n1 : $n1")
                        delay(500)
                    }
                }.join() // 17_ 작업 1이 끝날 때까지 다른 코루틴은 실행을 대기함.

                // 16_ 작업 2
                launch {
                    for(n1 in 200 until 211){
                        Log.d("TAG321","n1 : $n1")
                        delay(500)
                    }
                }
            }
        }
        
        
        binding.btn7.setOnClickListener { 
            CoroutineScope(Dispatchers.Default).launch {
                someTask()
            }
            Toast.makeText(this, "헬롱", Toast.LENGTH_SHORT).show()
        }


        // 코루틴의 제어
        var job: Job? = null
        binding.btn8.setOnClickListener {
            job = CoroutineScope(Dispatchers.Default).launch {
                for(n1 in 200 until 211){
                    Log.d("TAG321","n1 : $n1")
                    delay(500)
                }
            }
        }


        binding.btn9.setOnClickListener {
            job?.cancel()
        }
    }
    
    private suspend fun someTask(){
        for(n1 in 0 until 10000){
            Log.d("TAG321","n1 : $n1")
            delay(500)
            //binding.tv.text = "$n1"
        }
    }



}