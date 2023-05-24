package com.cha.ex96_hybridapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.cha.ex96_hybridapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1_ 웹뷰 설정들
        binding.wv.settings.javaScriptEnabled = true // JS 사용 허용
        binding.wv.settings.allowFileAccess = true // ajax 기술은 서버사이드(http://) 에서 동작함. 로컬에서 동작하도록 허용.

        binding.wv.webViewClient = WebViewClient()
        binding.wv.webChromeClient = WebChromeClient()

        // 11_ 웹뷰에서 사용할 메소드들을 가지고 있는 중계인 객체를 생성 및 웹뷰에 설정
        binding.wv.addJavascriptInterface(WebViewConnector(),"Droid") // 12_ "Droid" 라는 이름이 웹문서의 window 객체의 멤버로 자동 추가된다.



        // 2_ 웹뷰가 보여줄 웹문서(.html) 로드하기
        // 3_ html 을 만들자
        binding.wv.loadUrl("file:///android_asset/index.html")

        // 4_ HTML 을 직접 제어하는것은 불가능. JS 의 특정 함수를 호출하여 대신 제어.
        binding.btn.setOnClickListener {
            var msg = binding.et.text.toString()
            binding.wv.loadUrl("javascript: setMessage('$msg')")
            binding.et.setText("")
        }
    }// onCreate method..

    // 9_ 웹뷰의 JS 에서 호출할 수 있는 메소드를 모아놓은 클래스 설계
    inner class WebViewConnector{
        // 10_ javaScript 에서 호출할 수 있는 메소드
        @JavascriptInterface
        fun showMessage(msg: String){
            binding.tv.text = "웹뷰로부터 받은 메시지 : $msg"
        }

        // 14_ 디바이스의 고유기능인 사진선택 앱을 열어주는 기능 메소드
        @JavascriptInterface
        fun openPhotoApp(){
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivity(intent) // 15_ 원래는 startActivityForResult. 지금은 테스트 목적이므로 간단하게만..

            // 16_ 선택한 사진을 웹서버에 전송하고 웹문서에서 업로드된 사진파일을 보여주는 방식
        }
    }
}// MainActivity class..