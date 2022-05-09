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
        if (!Config.vibrate) return
        try {
            val pattern = LongArray(Config.vibrateCount * 2)
            val start = 100
            val duration = Config.vibrateDuration
            pattern.forEachIndexed { index, _ ->
                pattern[index] = index * duration + start
            }
            vibrator.vibrate(pattern, -1)
        } catch (e: Throwable) {
            e.printStackTrace()
            showToast("震动失败")
        }
    }

    fun playRing() {
        if (!Config.ring) return
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
        if (!Config.lightScreen) return
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
        playRing()
        vibrate()
        lightScreen()
    }
}