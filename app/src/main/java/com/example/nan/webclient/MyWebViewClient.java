package com.example.nan.webclient;

import android.webkit.WebView;

public class MyWebViewClient extends android.webkit.WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        //return super.shouldOverrideUrlLoading(view, url);
        return false;
    }
}
