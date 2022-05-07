package me.simple.baw

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val installedReceiver by lazy { InstalledReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        installedReceiver.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        installedReceiver.unregister(this)
    }
}