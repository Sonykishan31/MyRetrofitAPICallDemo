package com.example.retrofitdemo

import android.app.Application


class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
//        Fresco.initialize(this)

        appInstance = this

    }

    companion object {
        @JvmField
        var appInstance: AppClass? = null

        @JvmStatic
        fun getAppContext(): AppClass {
            return appInstance as AppClass
        }
    }
}