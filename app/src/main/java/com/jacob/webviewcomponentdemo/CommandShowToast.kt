package com.jacob.webviewcomponentdemo

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.auto.service.AutoService
import com.jacob.base.BaseApplication
import com.jacob.common.ICallBackFromMainProcessToWebViewProcessInterface
import com.jacob.common.autoservice.command.WebViewCommand

@AutoService(WebViewCommand::class)
class CommandShowToast : WebViewCommand {
    override fun name(): String {
        return "showToast"
    }

    override fun execute(
        parameters: MutableMap<*, *>,
        callback: ICallBackFromMainProcessToWebViewProcessInterface?
    ) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            Toast.makeText(
                BaseApplication.instance,
                parameters["message"].toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}