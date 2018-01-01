package com.qxkqyy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.qxkqyy.R;


public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView web1 = (WebView) findViewById(R.id.web1);
        web1.getSettings().setJavaScriptEnabled(true);
        web1.getSettings().setLightTouchEnabled(true);
        web1.getSettings().setDomStorageEnabled(true);
        web1.getSettings().setDatabaseEnabled(true);
        web1.getSettings().setAppCacheEnabled(true);

        web1.getSettings().setTextZoom(100);
        web1.loadUrl("http://www.qxkqyy.com");
        web1.setWebViewClient(new WebViewClient() {
            //点击网页中按钮时，在原页面打开
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView web1 = (WebView) findViewById(R.id.web1);
        if (keyCode == KeyEvent.KEYCODE_BACK && web1.canGoBack()) {
            web1.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
