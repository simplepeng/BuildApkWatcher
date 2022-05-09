package me.simple.baw

import android.content.Context
import android.media.RingtoneManager
import android.os.Vibrator
import android.util.Log
import android.widget.Toast

object Helper {

    private const val TAG = "Watcher"

    private val vibrator by lazy {
        App.ctx.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    fun showToast(text: String?) {
        Toast.makeText(App.ctx, text.orEmpty(), Toast.LENGTH_SHORT).show()
    }

    fun debug(text: String?) {
        Log.d(TAG, text.orEmpty())
    }

    fun vibrator() {
        vibrator.vibrate(longArrayOf(100, 700, 1300, 2000), -1)
    }

    fun playRing() {
        try {
            val ringUrl = RingtoneManager.getActualDefaultRingtoneUri(
                App.ctx,
                RingtoneManager.TYPE_NOTIFICATION
            )
            val ringtone = RingtoneManager.getRingtone(App.ctx, ringUrl)
            ringtone.play()
        } catch (e: Throwable) {
            showToast("播放系统默认铃声失败")
        }
    }

    fun notifyApkInstalled() {
        showToast("安装完成")
        vibrator()
    }
}