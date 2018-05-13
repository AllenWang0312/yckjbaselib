package com.yckj.baselib.common.base;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yckj.baselib.R;
import com.yckj.baselib.util.L;

/**
 * Created by wpc on 2018/4/18.
 */

//嵌入式的webview 和系统保持一致主题  放到 fragmentactivity使用
@SuppressLint("ValidFragment")
public class BaseWebViewFragment extends BaseBarFragment {

    String title;
    String url;
    String content;
    WebView webview;
    ProgressBar progress;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("url",url);
        outState.putString("content",content);
        super.onSaveInstanceState(outState);
    }

    public BaseWebViewFragment(){

    }
    public BaseWebViewFragment(String url) {
        this.url = url;
    }

    public BaseWebViewFragment(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_service_text, container, false);
        webview = view.findViewById(R.id.webview);
        progress = view.findViewById(R.id.loading);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        //支持插件
//        settings.setPluginsEnabled(true);
//设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
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

        //不使用缓存:
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        if (client == null) {
            client = new WebViewClient() {//默认自己处理重定向
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

            };
        }
        webview.setWebViewClient(client);

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.i("onProgressChanged", newProgress + "");
                if (newProgress == 100) {
                    progress.setVisibility(View.GONE);
                    if (title == null) {
                        Log.i("onProgressChanged", webview.getTitle());
                        setTitle(webview.getTitle());
                    }
//                    setTitle(web.getTitle());
                } else {
                    if (progress.getVisibility() == View.GONE) {
                        progress.setVisibility(View.VISIBLE);
                    }
                    progress.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });


//        String saveUrl= savedInstanceState.getString("url");
//        if(saveUrl!=null){
//            url=saveUrl;
//        }
//        savedInstanceState.get("content");
        return view;
    }

    WebViewClient client;

    public BaseWebViewFragment withWebViewClient(WebViewClient client) {//自定义拦截操作
        this.client = client;
        return this;
    }

    public void withOptions(int res_id,View.OnClickListener click){
        setRightIcon(res_id,click);
    }

    @Override
    public void onBack() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void setToolbar() {
        if (title != null) {
            setTitle(title);
        }
        enableBackIcon();
    }

    @Override
    public void refeshData() {

        if (url != null) {
            L.i("refreshDataa",url);
            setTitle(url);
            webview.loadUrl(url);
        } else {
            if (content != null) {
                webview.loadData(content, "text/html", "UTF-8");
            }
        }

    }

    @Override
    public boolean backable() {
        return webview.canGoBack();
    }
}
