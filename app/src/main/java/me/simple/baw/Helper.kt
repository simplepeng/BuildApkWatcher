package me.simple.baw

import android.util.Log
import android.widget.Toast

object Helper {

    private const val TAG = "Watcher"

    fun showToast(text: String?) {
        Toast.makeText(App.appCtx, text.orEmpty(), Toast.LENGTH_SHORT).show()
    }

    fun debug(text: String?) {
        Log.d(TAG, text.orEmpty())
    }

    fun notifyApkInstalled() {
        showToast("安装完成")
    }
}