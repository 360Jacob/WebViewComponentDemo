package com.jacob.webview.webViewProcess

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.jacob.base.BaseApplication
import com.jacob.common.ICallBackFromMainProcessToWebViewProcessInterface
import com.jacob.common.IWebViewProcessToMainProcessInterface
import com.jacob.webview.mainProcess.MainProcessCommandsService

object WebViewProcessCommandsDispatcher: ServiceConnection {
    private var iWebViewProcessToMainProcessInterface: IWebViewProcessToMainProcessInterface? = null

     fun initAidlConnection() {
        val intent = Intent(BaseApplication.instance, MainProcessCommandsService::class.java)
        BaseApplication.instance?.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        iWebViewProcessToMainProcessInterface =
            IWebViewProcessToMainProcessInterface.Stub.asInterface(service)

    }

    override fun onServiceDisconnected(name: ComponentName?) {
        iWebViewProcessToMainProcessInterface = null
        initAidlConnection()
    }

    fun executeCommand(commandName: String?, parameters: String, baseWebView: BaseWebView) {
        iWebViewProcessToMainProcessInterface?.handleWebCommand(
            commandName,
            parameters,
            object: ICallBackFromMainProcessToWebViewProcessInterface.Stub() {
                override fun onResult(callbackname: String?, response: String?) {
                    baseWebView.handleCallback(callbackname, response)
                }
            })
    }
}