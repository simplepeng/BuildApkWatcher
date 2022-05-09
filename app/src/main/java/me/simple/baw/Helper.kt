package me.simple.baw

import android.annotation.SuppressLint
import android.content.Context
import android.media.RingtoneManager
import android.os.PowerManager
import android.os.Vibrator
import android.util.Log
import android.widget.Toast

object Helper {

    private const val TAG = "Watcher"

    private val vibrator by lazy {
        App.ctx.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    private val powerManager by lazy {
        App.ctx.getSystemService(Context.POWER_SERVICE) as PowerManager
    }

    fun showToast(text: String?) {
        Toast.makeText(App.ctx, text.orEmpty(), Toast.LENGTH_SHORT).show()
    }

    fun debug(text: String?) {
        Log.d(TAG, text.orEmpty())
    }

    fun vibrate() {
        try {
            vibrator.vibrate(longArrayOf(100, 700, 1300, 2000), -1)
        } catch (e: Throwable) {
            e.printStackTrace()
            showToast("震动失败")
        }
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
            e.printStackTrace()
            showToast("播放系统默认铃声失败")
        }
    }

    @SuppressLint("InvalidWakeLockTag")
    fun lightScreen() {
        try {
            val wakeLock = powerManager.newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK
                        or PowerManager.ACQUIRE_CAUSES_WAKEUP,
                "ff"
            )
            wakeLock.acquire(2000)
        } catch (e: Throwable) {
            e.printStackTrace()
            showToast("亮屏失败")
        }
    }

    fun notifyApkInstalled() {
        showToast("安装完成")
        lightScreen()
        playRing()
        vibrate()
    }
}