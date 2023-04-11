package com.trips.pk

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            goToMainActivity()
        },1000)
    }

    private fun goToMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}