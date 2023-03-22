package com.example.ex93_kotlinopenapinaversearch

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ex93_kotlinopenapinaversearch.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener(object : OnClickListener {
            override fun onClick(p0: View?) {
                searchData()
            }
        })
    }

    fun searchData(){
        // 18_ 소프트 키보드 없애기
        val imm:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken,0)
        // 1_ Naver 쇼핑 검색 API 사용해보기

        // 2_ 네트워크 작업을 대신 작성해주는 라이브러리 활용 : Retrofit

        // 3_ 1단계 Retrofit 생성
        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 4_ 요구명세 작성하기( 인터페이스 설계 및 추상 메소드 정의)
        // RetrofitService.kt 라는 클래스를 만들자.

        // 5_ RetrofitService 객체 생성하기
        val retrofitService:RetrofitService = retrofit.create(RetrofitService::class.java)

//        // 6_ 원하는 작업을 요청하여 네트워크 작업을 실행하는 객체를 리턴받자.
//        val call: Call<String> = retrofitService.searchDataByString("bED_Bn4IzCayX3KnlLXq","nc1pKiIc6F",binding.et.text.toString())
//
//        // 7_ 작업 시작
//        call.enqueue(object : Callback<String>{
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                var s:String? = response.body()
//
//                AlertDialog.Builder(this@MainActivity).setMessage(s).create().show()
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "실패", Toast.LENGTH_SHORT).show()
//            }
//        })
        // 14_ 원하는 작업을 요청하자.
        val call: Call<NaverSearchApiResponse> = retrofitService.searchData(binding.et.text.toString())
        
        // 15_ 작업 시작
        call.enqueue(object : Callback<NaverSearchApiResponse>{
            override fun onResponse(
                call: Call<NaverSearchApiResponse>,
                response: Response<NaverSearchApiResponse>
            ) {
                val naverSearchApiResponse:NaverSearchApiResponse? = response.body()
                // Toast.makeText(this@MainActivity, "아이템 개수 : ${naverSearchApiResponse?.items?.size}", Toast.LENGTH_SHORT).show()
                binding.recycler.adapter = MyAdapter(this@MainActivity,naverSearchApiResponse!!.items)
            }

            override fun onFailure(call: Call<NaverSearchApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "불러오기 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }
}