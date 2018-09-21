package com.yckj.baselib.common.base;//package com.yanchuan.SuiQi.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yckj.baselib.R;

import static android.view.KeyEvent.KEYCODE_BACK;


public class BaseWebViewActivity extends BaseBarActivity {

    String title;
    private String url;
    String content;
    private WebView webview;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");

        title = getIntent().getStringExtra("title");

        content = getIntent().getStringExtra("content");

        if (url != null) {
            Log.i("web href", url);
        }
        setContentView(R.layout.activity_service_text);
//        bindToolbar(R.id.toolbar);
//        R.drawable.anglered
        setToolbar(R.id.toolbar);
        initView();
        initDate();
    }


    @Override
    public void setToolbar(int id) {
        enableBackIcon();
        setTitle(title);
    }

    private void initView() {
        webview = (WebView) findViewById(R.id.webview);
        progress = (ProgressBar) findViewById(R.id.loading);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        //支持插件
//        settings.setPluginsEnabled(true);
//设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
//        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
//        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
//        webSettings.setAllowFileAccess(true); //设置可以访问文件
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");///设置编码格式

        //优先使用缓存:
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
//        复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
    }
    private void initDate() {
        //        webview.setWebViewClient(new WebViewClient());
        if (url != null) {
            webview.loadUrl(url);
        } else {
            if (content != null) {
                webview.loadData(content, "text/html", "UTF-8");
            }
        }
    }

    public static void start(Context activity, String title, String url) {
        activity.startActivity(new Intent(activity, BaseWebViewActivity.class)
                .putExtra("url", url)
                .putExtra("title", title));
    }

    public static void startWithContent(FragmentActivity activity, String title, String content) {
        activity.startActivity(new Intent(activity, BaseWebViewActivity.class)
                .putExtra("content", content)
                .putExtra("title", title));
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        //由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序.
//        webview.clearCache(true);
        //清除当前webview访问的历史记录
//只会webview访问历史记录里的所有记录除了当前访问记录
        webview.clearHistory();
        //这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
//        Webview.clearFormData()；
        super.onDestroy();
    }
}
