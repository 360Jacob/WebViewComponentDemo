package com.jacob.webview.mainProcess

import android.util.Log
import com.google.gson.Gson
import com.jacob.common.ICallBackFromMainProcessToWebViewProcessInterface
import com.jacob.common.IWebViewProcessToMainProcessInterface
import com.jacob.common.autoservice.command.WebViewCommand
import java.util.*
import kotlin.collections.HashMap
object MainProcessCommandsManager: IWebViewProcessToMainProcessInterface.Stub() {
     var TAG = "MainProcessCommandsManager"
    private val mCommands: HashMap<String, WebViewCommand> = HashMap<String, WebViewCommand>()
    init {
        val serviceLoader = ServiceLoader.load(WebViewCommand::class.java)
        for (webViewCommand in serviceLoader) {
            if(!mCommands.containsKey(webViewCommand.name())){
                mCommands[webViewCommand.name()] = webViewCommand
            }
        }
    }

    override fun handleWebCommand(
        commandName: String?,
        jsonParams: String?,
        callback: ICallBackFromMainProcessToWebViewProcessInterface?
    ) {
        Log.i(TAG, "Main process commands manager handle web command")
        mCommands[commandName]?.execute(Gson().fromJson(jsonParams, HashMap::class.java), callback)
    }

}