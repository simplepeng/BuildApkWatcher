package me.simple.baw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private val btnStart: View by lazy { findViewById(R.id.btnStart) }

    private val installedReceiver by lazy { InstalledReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            KeepAliveService.start(this)
            installedReceiver.register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        installedReceiver.unregister(this)
    }
}