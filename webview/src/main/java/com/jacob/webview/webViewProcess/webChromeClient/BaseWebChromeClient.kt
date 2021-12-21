package com.jacob.webview.webViewProcess.webChromeClient

import android.webkit.WebChromeClient
import android.webkit.WebView
import com.jacob.webview.webViewProcess.webViewClient.BaseWebViewCallBack

class BaseWebChromeClient() : WebChromeClient() {
    private lateinit var mCallBack: BaseWebViewCallBack
    constructor(callBack: BaseWebViewCallBack):this(){
        mCallBack = callBack
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
        mCallBack.updateTitle(title)
    }
}