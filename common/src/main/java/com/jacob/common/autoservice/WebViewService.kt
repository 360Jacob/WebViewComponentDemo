package com.jacob.common.autoservice

import android.content.Context

interface WebViewService {

    fun startWebViewActivity(context: Context, url: String, isSHowActionBar: Boolean, title: String,nativeRefreshEnable: Boolean)

//    fun startWebViewFragment(
//        context: Context,
//        url: String,
//        isSHowActionBar: Boolean,
//        title: String,
//        nativeRefreshEnable: Boolean
//    )
}