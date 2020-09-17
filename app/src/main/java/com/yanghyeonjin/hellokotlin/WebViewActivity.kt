package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.yanghyeonjin.hellokotlin.databinding.ActivityWebViewBinding
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 웹뷰 안에 자바스크립트가 있으면 그것을 허용해 주겠니?
        binding.webView.settings.javaScriptEnabled = true

        // 휴대폰 브라우저 앱이 아닌 현재 앱 내부에서 화면을 확인 할 수 있게 해주는 옵션
        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = WebChromeClient()

        // URL
        binding.webView.loadUrl("https://www.naver.com")
    }

    override fun onBackPressed() {

        // 웹뷰 뒤로가기 처리
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}