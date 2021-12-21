package com.jacob.webviewcomponentdemo

import android.os.RemoteException
import android.util.Log
import com.google.auto.service.AutoService
import com.google.common.collect.Multimap
import com.google.common.eventbus.EventBus
import com.google.common.eventbus.Subscribe
import com.google.gson.Gson
import com.jacob.base.utils.MyServiceLoader
import com.jacob.common.ICallBackFromMainProcessToWebViewProcessInterface
import com.jacob.common.autoservice.IUserCenterService
import com.jacob.common.autoservice.command.WebViewCommand
import com.jacob.common.autoservice.eventbus.LoginEvent

@AutoService(WebViewCommand::class)
class CommandLogin: WebViewCommand{
    var iUserCenterService: IUserCenterService = MyServiceLoader.load(IUserCenterService::class.java)!!
    var callback: ICallBackFromMainProcessToWebViewProcessInterface? = null
    var callbacknameFromNativeJs: String? = null
    constructor(){
        EventBus().register(this)
    }
    override fun name(): String {
        return "login"
    }

    override fun execute(
        parameters: MutableMap<*, *>,
        callback: ICallBackFromMainProcessToWebViewProcessInterface?
    ) {
        Log.d("CommandLogin", Gson().toJson(parameters))
        if (iUserCenterService != null && !iUserCenterService.isLogined()) {
            iUserCenterService.login()
            this.callbacknameFromNativeJs = parameters["callbackname"].toString() //callback js
        }
    }

    @Subscribe
    fun onMessageEvent(event: LoginEvent) {
        Log.d("CommandLogin", event.userName!!)
        val map = HashMap<String,String> ()
        map["accountName"] = event.userName!!
        if (callback != null) {
            try {
                callback!!.onResult(callbacknameFromNativeJs, Gson().toJson(map))
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }
}
