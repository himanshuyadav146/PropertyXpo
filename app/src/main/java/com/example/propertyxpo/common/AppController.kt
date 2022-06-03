package com.example.propertyxpo.common

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class AppController : Application() {

    private var mInstance: AppController? = null

    init {
        instance = this
    }

    companion object {
        private var instance: AppController? = null

        fun applicationContext() : AppController {
            return instance as AppController
        }
    }

    override fun onCreate() {
        super.onCreate()
    }


}