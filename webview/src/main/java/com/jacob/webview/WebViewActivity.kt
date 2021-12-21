package com.jacob.webview

import android.view.View
import com.jacob.base.view.BaseActivity
import com.jacob.webview.webViewProcess.webChromeClient.BaseWebChromeClient
import com.jacob.webview.webViewProcess.webViewClient.BaseWebViewClient
import com.jacob.webview.webViewProcess.webViewClient.BaseWebViewCallBack
import com.jacob.webview.constants.WebConstant
import com.jacob.webview.databinding.ActivityWebviewBinding
import com.jacob.webview.webViewProcess.utils.WebViewDefaultSettings

class WebViewActivity : BaseActivity<ActivityWebviewBinding>(), BaseWebViewCallBack {
    override fun initView() {
//        mBinding.webView.settings.javaScriptEnabled = true
//        WebViewDefaultSettings.getInstance()?.setSettings(mBinding.webView)
//        mBinding.webView.webViewClient = BaseWebViewClient(this)
//        mBinding.webView.webChromeClient = BaseWebChromeClient(this)
        mBinding.webView.registerWebViewCallBack(this)
    }

    override fun initData() {
        var url = intent?.getStringExtra(WebConstant.URL) as String
        var isShowActionBar =
            intent?.getBooleanExtra(WebConstant.IS_SHOW_ACTION_BAR, false) as Boolean
        var nativeRefreshEnable =
            intent?.getBooleanExtra(WebConstant.NATIVE_REFRESH_ENABLE, false) as Boolean
        var title = intent?.getStringExtra(WebConstant.TITLE)
        updateTitle(title)
        mBinding.sfl.setEnableRefresh(nativeRefreshEnable)
        mBinding.sfl.setOnRefreshListener {
            mBinding.webView.reload()
        }

        if (isShowActionBar) {
            mBinding.toolbar.visibility = View.VISIBLE
        } else {
            mBinding.toolbar.visibility = View.GONE
        }
        mBinding.webView.loadUrl(url)
    }

    override fun showLoading() {
    }

    override fun onPageStarted(url: String?) {
    }

    override fun onPageFinished(url: String?) {
        mBinding.sfl.finishRefresh()
    }

    override fun onError() {
        mBinding.sfl.finishRefresh()
    }

    override fun updateTitle(title: String?) {
        mBinding.toolbar.title = title
    }

}