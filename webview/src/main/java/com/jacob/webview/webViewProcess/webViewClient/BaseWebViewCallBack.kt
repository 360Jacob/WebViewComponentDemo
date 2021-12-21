package com.jacob.webview.webViewProcess.webViewClient

interface BaseWebViewCallBack {
    fun onPageStarted(url: String?)

    fun onPageFinished(url: String?)

    fun onError()

    fun updateTitle(title: String?)
}