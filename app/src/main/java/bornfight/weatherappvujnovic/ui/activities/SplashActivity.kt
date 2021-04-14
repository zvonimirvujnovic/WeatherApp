package bornfight.weatherappvujnovic.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bornfight.weatherappvujnovic.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}