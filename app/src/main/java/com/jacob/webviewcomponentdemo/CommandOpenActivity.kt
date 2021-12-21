package com.jacob.webviewcomponentdemo

import android.content.ComponentName
import android.content.Intent
import android.text.TextUtils
import com.google.auto.service.AutoService
import com.jacob.base.BaseApplication
import com.jacob.common.ICallBackFromMainProcessToWebViewProcessInterface
import com.jacob.common.autoservice.command.WebViewCommand

@AutoService(WebViewCommand::class)
class CommandOpenActivity: WebViewCommand{
    override fun name(): String {
        return "openPage"
    }

    override fun execute(
        parameters: MutableMap<*, *>,
        callback: ICallBackFromMainProcessToWebViewProcessInterface?
    ) {
        val targetClass = parameters["target_class"].toString()
        if (!TextUtils.isEmpty(targetClass)) {
            val intent = Intent()
            intent.component =
                ComponentName(BaseApplication.instance!!, targetClass)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            BaseApplication.instance?.startActivity(intent)
        }
    }
}