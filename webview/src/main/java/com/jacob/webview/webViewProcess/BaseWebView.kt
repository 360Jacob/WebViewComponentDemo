package com.jacob.webview.webViewProcess

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.google.gson.Gson
import com.jacob.webview.webViewProcess.utils.WebViewDefaultSettings
import com.jacob.webview.webViewProcess.webChromeClient.BaseWebChromeClient
import com.jacob.webview.webViewProcess.webViewClient.BaseWebViewCallBack
import com.jacob.webview.webViewProcess.webViewClient.BaseWebViewClient
import com.jacob.webview.entity.JsParam

class BaseWebView : WebView {
   var TAG="BaseWebView"
    constructor(context: Context): super(context){
        init()
    }
    constructor(context:Context, attrs: AttributeSet?): super(context , attrs){
        init()
    }

     constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context!!, attrs,  defStyleAttr) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context!!, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    @SuppressLint("JavascriptInterface")
    fun init(){
        WebViewProcessCommandsDispatcher.initAidlConnection()
        addJavascriptInterface(this, "xiangxuewebview");
        WebViewDefaultSettings.getInstance().setSettings(this);
    }
    fun registerWebViewCallBack(webViewCallBack: BaseWebViewCallBack?) {
        webViewClient = BaseWebViewClient(webViewCallBack!!)
        webChromeClient = BaseWebChromeClient(webViewCallBack)
    }
    @JavascriptInterface
    fun takeNativeAction(jsParam: String){
        Log.e(TAG, "this is a call from html javascript.$jsParam")
        if (!TextUtils.isEmpty(jsParam)) {
            val jsParamObject: JsParam = Gson().fromJson(jsParam, JsParam::class.java)
            if (jsParamObject != null) {
                WebViewProcessCommandsDispatcher.executeCommand(
                    jsParamObject.name,
                    Gson().toJson(jsParamObject.param),
                    this
                )
            }
        }
    }

    fun handleCallback(callbackname: String?, response: String?) {
        if (!TextUtils.isEmpty(callbackname) && !TextUtils.isEmpty(response)) {
            post {
                val jscode = "javascript:xiangxuejs.callback('$callbackname',$response)"
                evaluateJavascript(jscode, null)
            }
        }
    }
}