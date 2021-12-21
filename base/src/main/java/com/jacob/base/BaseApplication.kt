package com.jacob.base

import android.app.Application

open class BaseApplication: Application() {
    companion object {
        var instance: Application? = null
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}