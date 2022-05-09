package me.simple.baw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val btnStart: View by lazy { findViewById(R.id.btnStart) }

    private val cbLightScreen: CheckBox by lazy { findViewById(R.id.cbLight) }
    private val cbRing: CheckBox by lazy { findViewById(R.id.cbRing) }
    private val cbVibrate: CheckBox by lazy { findViewById(R.id.cbVibrate) }
    private val etVibrateCount: EditText by lazy { findViewById(R.id.etVibrateCount) }
    private val etVibrateDuration: EditText by lazy { findViewById(R.id.etVibrateDuration) }

    private val installedReceiver by lazy { InstalledReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readConfig()

        btnStart.setOnClickListener {
            saveConfig()
            KeepAliveService.start(this)
            installedReceiver.register(this)
        }
    }

    private fun readConfig() {
        cbLightScreen.isChecked = Config.lightScreen
        cbRing.isChecked = Config.ring
        cbVibrate.isChecked = Config.vibrate
        etVibrateCount.setText(Config.vibrateCount.toString())
        etVibrateDuration.setText(Config.vibrateDuration.toString())
    }

    private fun saveConfig() {
        Config.lightScreen = cbLightScreen.isChecked
        Config.ring = cbRing.isChecked
        Config.vibrate = cbVibrate.isChecked
        Config.vibrateCount = etVibrateCount.text.toString().toInt()
        Config.vibrateDuration = etVibrateDuration.text.toString().toLong()
    }

    override fun onDestroy() {
        super.onDestroy()
        installedReceiver.unregister(this)
    }
}