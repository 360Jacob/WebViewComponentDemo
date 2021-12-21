package com.jacob.webview.webViewProcess.webViewClient

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class BaseWebViewClient() : WebViewClient() {
    var TAG ="BaseWebViewClient"
    private lateinit var mCallBack: BaseWebViewCallBack

    constructor(callBack: BaseWebViewCallBack) : this() {
        mCallBack = callBack;
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        if(mCallBack != null){
            mCallBack?.onPageStarted(url)
        }else{
            Log.e(TAG, "WebViewCallBack is null.");
        }
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        if(mCallBack != null){
            mCallBack?.onPageFinished(url)
        }else{
            Log.e(TAG, "WebViewCallBack is null.");
        }
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        if(mCallBack != null){
            mCallBack?.onError()
        }else{
            Log.e(TAG, "WebViewCallBack is null.");
        }

    }
}