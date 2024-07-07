package com.example.jogodavelha

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Delay of 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            // Start main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Finish splash activity
            finish()
        }, 2000) // 2000 milliseconds = 2 seconds
    }
}