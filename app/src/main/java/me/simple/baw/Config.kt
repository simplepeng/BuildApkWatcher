package me.simple.baw

import android.content.Context
import androidx.core.content.edit

object Config {

    private val sp by lazy { App.ctx.getSharedPreferences("config", Context.MODE_PRIVATE) }

    var lightScreen: Boolean
        get() = sp.getBoolean("light_screen", true)
        set(value) = sp.edit { putBoolean("light_screen", value) }

    var vibrate: Boolean
        get() = sp.getBoolean("vibrate", true)
        set(value) = sp.edit { putBoolean("vibrate", value) }

    var vibrateCount: Int
        get() = sp.getInt("vibrate_count", 2)
        set(value) = sp.edit { putInt("vibrate_count", value) }

    var vibrateDuration: Long
        get() = sp.getLong("vibrate_duration", 500)
        set(value) = sp.edit { putLong("vibrate_duration", value) }

    var ring: Boolean
        get() = sp.getBoolean("ring", true)
        set(value) = sp.edit { putBoolean("ring", value) }
}