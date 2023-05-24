package com.cha.ex95_webapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {

    val wv: WebView by lazy { findViewById(R.id.wv) }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1_ webview 의 기본 설정
        wv.settings.javaScriptEnabled = true // 웹뷰 설정객체를 통해 JS 사용을 허용하도록 변경 설정
        wv.webViewClient = WebViewClient() // 새로운 웹문서가 열릴때 기본 웹뷰는 새탭으로 열기에 이 웹뷰가 웹브라우저가 실행되면서 열림
        wv.webChromeClient = WebChromeClient() // alert(), confirm() 같은 팝업기능을 사용하도록 설정.

        // 2_ 웹뷰가 보여줄 웹문서 (.html) 을 로드하자
        // 웹문서가 있는 위치는 프로젝트 안에 있거나, 웹 서버에 위치시켜야 한다.

        // 3_ 1) 프로젝트 안에서 assets 폴더안에 html 을 위치시킨다. -> assets 안드로이드 기본 폴더를 만들고 index.html 로 이동~~
        // wv.loadUrl("file:///android_asset/index.html")

        // 6_ 2) 닷홈 or AWS 같은 웹서버에 html 문서가 존재
        // wv.loadUrl("http://tjdrjs0803.dothome.co.kr/index.html")
        wv.loadUrl("http://tjdrjs0803.dothome.co.kr/TeamProject")
    }

    override fun onBackPressed() {
        if(wv.canGoBack()) wv.goBack()
        else finish()
    }
}