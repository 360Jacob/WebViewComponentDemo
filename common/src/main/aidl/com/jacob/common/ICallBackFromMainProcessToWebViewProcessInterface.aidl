// ICallBackFromMainProcessToWebViewProcessInterface.aidl
package com.jacob.common;

// Declare any non-default types here with import statements

interface ICallBackFromMainProcessToWebViewProcessInterface {
    void onResult(String kotlinToJavescriptCallBackName, String response);
}