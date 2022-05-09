package me.simple.baw

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class KeepAliveService : Service() {

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, KeepAliveService::class.java)
            ContextCompat.startForegroundService(context, starter)
        }

        private const val notifyId = 12
        private const val channelId = "keep_alive_ch"
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotification() {
        try {
            val channelCompat = NotificationChannelCompat.Builder(
                channelId,
                NotificationManagerCompat.IMPORTANCE_DEFAULT
            )
                .setName("监听安装保活")
                .build()

            val nmManagerCompat = NotificationManagerCompat.from(App.ctx)
            nmManagerCompat.createNotificationChannel(channelCompat)

            val nm = NotificationCompat.Builder(App.ctx, channelId)
                .setChannelId(channelCompat.id)
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(getString(R.string.app_name))
                .setContentText("监听服务运行中...")
                .build()

            startForeground(notifyId, nm)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    override fun onCreate() {
        super.onCreate()
        createNotification()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Helper.showToast("启动服务成功")
        return super.onStartCommand(intent, flags, startId)
    }
}