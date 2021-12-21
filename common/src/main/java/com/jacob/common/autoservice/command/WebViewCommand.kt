package com.jacob.common.autoservice.command

import com.jacob.common.ICallBackFromMainProcessToWebViewProcessInterface

interface WebViewCommand {
    fun name():String
    fun execute(parameters: MutableMap<*, *>, callback: ICallBackFromMainProcessToWebViewProcessInterface?)
}