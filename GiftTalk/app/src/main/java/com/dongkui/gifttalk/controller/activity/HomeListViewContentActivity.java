package com.dongkui.gifttalk.controller.activity;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.dongkui.gifttalk.R;

/**
 * Created by dllo on 16/9/27.
 */
public class HomeListViewContentActivity extends AbsBaseActivity {
    private WebView webView;
    private TextView likeCountTv;

    @Override
    protected int setLayout() {
        return R.layout.activity_home_lv_content;
    }

    @Override
    protected void initView() {
        webView = byView(R.id.home_web_view);
        likeCountTv = byView(R.id.home_lv_content_like_count);

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("position");
        String likeCount = String.valueOf(intent.getStringExtra("likeCount"));

        Log.d("HomeListViewContentActi", intent.getStringExtra("likeCount") + "");
        Log.d("HomeListViewContentActi", url);

        // 设置不跳转浏览器, 在当前Aty上显示
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);
            }
        });

        // 设置WebView加载网页的属性  WebSettings
        WebSettings set = webView.getSettings();
        // 让WebView能够执行javaScript(设置java脚本启动)
        set.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        set.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        set.setAppCacheEnabled(true);
        // 设置缓存模式
        set.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
//        set.setAppCachePath("");
        // 支持缩放(适配当前的屏幕)
        set.setSupportZoom(false);
        // 调整图片的大小
        set.setUseWideViewPort(false);

        // 加载网址
        webView.loadUrl(url);

        // 显示喜欢的数量
        likeCountTv.setText(likeCount);

    }
}
