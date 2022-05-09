package me.simple.baw

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

@SuppressLint("StaticFieldLeak")
class App : Application() {

    companion object {

        lateinit var appCtx: Context

    }

    override fun onCreate() {
        super.onCreate()
        appCtx = this
    }
}