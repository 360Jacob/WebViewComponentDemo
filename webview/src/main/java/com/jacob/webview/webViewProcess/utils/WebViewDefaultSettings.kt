package com.jacob.webview.webViewProcess.utils

import android.os.Build
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import com.jacob.base.utils.NetWorkUtils

class WebViewDefaultSettings {
    companion object {

        private  var intances : WebViewDefaultSettings = WebViewDefaultSettings()

        fun getInstance(): WebViewDefaultSettings {
            if (intances == null) {
                synchronized(this) {
                    if (intances == null) {
                        intances = WebViewDefaultSettings()
                    }
                }
            }
            return intances
        }
    }

    fun setSettings(webView: WebView) {
        var settings = webView.settings // 声明 WebSetting 子类

        settings.javaScriptEnabled = true  // 开启 JavaScript 交互
        settings.setAppCacheEnabled(true) // 启用或禁用缓存
        settings.cacheMode = WebSettings.LOAD_DEFAULT // 只要缓存可用就加载缓存, 哪怕已经过期失效 如果缓存不可用就从网络上加载数据
//        settings.setAppCachePath(cacheDir.path) // 设置应用缓存路径
        if (NetWorkUtils.isNetworkConnected(webView.context)) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        // 缩放操作
        settings.setSupportZoom(false) // 支持缩放 默认为true 是下面那个的前提
        settings.builtInZoomControls = false // 设置内置的缩放控件 若为false 则该WebView不可缩放
        settings.displayZoomControls = false // 隐藏原生的缩放控件

        settings.blockNetworkImage = false // 禁止或允许WebView从网络上加载图片
        settings.loadsImagesAutomatically = true // 支持自动加载图片

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true // 是否开启安全模式
        }

        settings.javaScriptCanOpenWindowsAutomatically = true // 支持通过JS打开新窗口
        settings.domStorageEnabled = true // 启用或禁用DOM缓存
        settings.setSupportMultipleWindows(true) // 设置WebView是否支持多窗口

        // 设置自适应屏幕, 两者合用
        settings.useWideViewPort = true  // 将图片调整到适合webview的大小
        settings.loadWithOverviewMode = true  // 缩放至屏幕的大小
        settings.allowFileAccess = true // 设置可以访问文件

        settings.setGeolocationEnabled(true) // 是否使用地理位置

        webView.fitsSystemWindows = true
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
    }
}