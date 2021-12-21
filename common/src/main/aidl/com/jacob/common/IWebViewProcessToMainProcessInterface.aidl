// IWebViewProcessToMainProcessInterface.aidl
package com.jacob.common;

// Declare any non-default types here with import statements
import com.jacob.common.ICallBackFromMainProcessToWebViewProcessInterface;
interface IWebViewProcessToMainProcessInterface {
//     void handleWebCommand(String commandName, String jsonParams);
     void handleWebCommand(String commandName, String jsonParams, in ICallBackFromMainProcessToWebViewProcessInterface callback);
}