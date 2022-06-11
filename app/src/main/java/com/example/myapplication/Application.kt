package com.example.myapplication

import android.app.Application
import android.util.Log

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    fun log(str: String) {
        Log.e("Application", str)
    }
}