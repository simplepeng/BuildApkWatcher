package me.simple.baw

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.util.concurrent.atomic.AtomicBoolean

class InstalledReceiver : BroadcastReceiver() {

    private val mHandler = Handler(Looper.getMainLooper())

    fun register(context: Context) {
        val intentFilter = IntentFilter().apply {
//            addAction(Intent.ACTION_PACKAGE_ADDED)
//            addAction(Intent.ACTION_PACKAGE_REPLACED)
//            addAction(Intent.ACTION_PACKAGE_REMOVED)
            addAction(Intent.ACTION_PACKAGE_RESTARTED)
//            addAction(Intent.ACTION_PACKAGE_CHANGED)
            addDataScheme("package")
        }
        context.registerReceiver(this, intentFilter)
    }

    fun unregister(
        context: Context
    ) {
        context.unregisterReceiver(this)
    }

    private val isNotified = AtomicBoolean(false)

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        Log.d("InstalledReceiver", action.orEmpty())
        if (action == Intent.ACTION_PACKAGE_RESTARTED && !isNotified.get()) {
            isNotified.set(true)
            Helper.notifyApkInstalled()
            mHandler.postDelayed(run,5000)
        }
    }

    private val run = Runnable {
        isNotified.set(false)
    }

}