package com.jacob.webviewdemo

import com.jacob.base.utils.MyServiceLoader
import com.jacob.base.view.BaseActivity
import com.jacob.common.autoservice.WebViewService
import com.jacob.webviewdemo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val webViewService = MyServiceLoader.load(WebViewService::class.java)

    override fun initView() {
        mBinding.btnStartActivity.setOnClickListener {
//            webViewService?.startWebViewActivity(this, "https://www.baidu.com", true, "test",true)
            webViewService?.startWebViewActivity(this, "file:///android_asset/demo.html", true, "test",true)
        }
//        mBinding.btnStartFragment.setOnClickListener{
//            webViewService?.startWebViewFragment(
//                this,
//                "https://www.baidu.com",
//                true,
//                "test",
//                false
//            )
//        }

    }

    override fun initData() {
    }

    override fun showLoading() {
    }
}