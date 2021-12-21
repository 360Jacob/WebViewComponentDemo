package com.jacob.base.utils

import android.util.Log
import java.util.*

object MyServiceLoader  {
    const val TAG = "MyServiceLoader"
    fun<s> load(service : Class<s>) : s?{
        return try {
            ServiceLoader.load(service).iterator().next()
        }catch (e : Exception){
            Log.e(TAG, service?.simpleName )
            null
        }
    }
}