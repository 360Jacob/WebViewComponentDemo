package com.jacob.webview

import android.content.Context
import android.content.Intent
import com.google.auto.service.AutoService
import com.jacob.common.autoservice.WebViewService
import com.jacob.webview.constants.WebConstant

@AutoService(WebViewService::class)
class WebViewServiceImpl : WebViewService  {

    override fun startWebViewActivity(context: Context, url: String, isSHowActionBar: Boolean, title: String,nativeRefreshEnable: Boolean ) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(WebConstant.URL, url)
        intent.putExtra(WebConstant.IS_SHOW_ACTION_BAR, isSHowActionBar)
        intent.putExtra(WebConstant.TITLE, url)
        intent.putExtra(WebConstant.NATIVE_REFRESH_ENABLE, nativeRefreshEnable)
        context.startActivity(intent)
    }

//    override fun startWebViewFragment(
//        context: Context,
//        url: String,
//        isSHowActionBar: Boolean,
//        title: String,
//        nativeRefreshEnable: Boolean
//    ) {
//        val intent = Intent(context, WebViewFragmentActivity::class.java)
//        intent.putExtra(WebConstant.URL, url)
//        intent.putExtra(WebConstant.IS_SHOW_ACTION_BAR, isSHowActionBar)
//        intent.putExtra(WebConstant.TITLE, url)
//        intent.putExtra(WebConstant.NATIVE_REFRESH_ENABLE, nativeRefreshEnable)
//        context.startActivity(intent)
//    }


}