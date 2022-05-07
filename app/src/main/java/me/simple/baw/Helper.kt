package me.simple.baw

import android.content.Context
import android.os.Vibrator
import android.util.Log
import android.widget.Toast

object Helper {

    private const val TAG = "Watcher"

    private val vibrator by lazy {
        App.appCtx.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    fun showToast(text: String?) {
        Toast.makeText(App.appCtx, text.orEmpty(), Toast.LENGTH_SHORT).show()
    }

    fun debug(text: String?) {
        Log.d(TAG, text.orEmpty())
    }

    fun vibrator() {
        vibrator.vibrate(longArrayOf(100, 700, 1300, 2000), -1)
    }

    fun notifyApkInstalled() {
        showToast("安装完成")
        vibrator()
    }
}