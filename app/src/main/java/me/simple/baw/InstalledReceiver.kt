package me.simple.baw

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class InstalledReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        Log.d("InstalledReceiver", action.orEmpty())
        if (action == Intent.ACTION_PACKAGE_ADDED) {
            Log.d("InstalledReceiver", "ACTION_PACKAGE_ADDED")
        }
    }
}