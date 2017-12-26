package com.qxkqyy

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onStart() {
        super.onStart()
        val web1 = findViewById(R.id.web1) as WebView
        web1.settings.javaScriptEnabled = true
        web1.settings.lightTouchEnabled = true
        web1.settings.domStorageEnabled = true
        web1.settings.databaseEnabled = true
        web1.settings.setAppCacheEnabled(false)
        web1.settings.textZoom = 100
        web1.loadUrl("https://1970633640.github.io/webapp/1.html")
        web1.setWebViewClient(object : WebViewClient() {
            //点击网页中按钮时，在原页面打开
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })
    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.upin, R.anim.upout2)
    }
}
