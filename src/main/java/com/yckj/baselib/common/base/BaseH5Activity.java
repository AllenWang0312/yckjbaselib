package com.yckj.baselib.common.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yckj.baselib.R;
import com.yckj.baselib.common.H5InterfaceBean;
import com.yckj.baselib.util.L;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/3 0003 下午 1:09
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public abstract class BaseH5Activity<T extends H5InterfaceBean> extends BaseBarActivity {

    WebView web;
    ProgressBar progress;
    protected String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_h5);
        bindToolbar(R.id.toolbar);
        web = findViewById(R.id.web);
        progress = findViewById(R.id.progress);
        enableBackIcon();
        //这里添加JS的交互事件，这样H5就可以调用原生的代码
        WebSettings settings = web.getSettings();

        settings.setJavaScriptEnabled(true);  //设置运行使用JS
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");///设置编码格式

        //优先使用缓存:
//        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。

        //不使用缓存:
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        //添加客户端支持
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progress.setVisibility(View.GONE);
                    setTitle(web.getTitle());
                } else {
                    if (progress.getVisibility() == View.GONE) {
                        progress.setVisibility(View.VISIBLE);
                    }
                    progress.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                L.i("shouldOverrideUrlLoading", url);
//                view.loadUrl(url);
                return false;
            }
        });

        web.loadUrl(getUrl());

        web.addJavascriptInterface(getH5Interface(), "button");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

    }

    public abstract T getH5Interface();

    public abstract String getUrl();
    @Override
    public void setToolbar() {
        enableBackIcon();
        setTitle("订单详情");
        if (web != null) {
            setTitle(web.getTitle());
        }
    }

    protected void refreshUrl() {
        web.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }

    }

}
