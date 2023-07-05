package com.si.Views

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

import android.content.Intent
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@MainActivity, SplashScreen::class.java))
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}