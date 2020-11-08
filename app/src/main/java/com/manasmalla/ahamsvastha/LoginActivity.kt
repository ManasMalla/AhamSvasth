package com.manasmalla.ahamsvastha

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController
            controller?.hide(WindowInsets.Type.statusBars() + WindowInsets.Type.navigationBars())
            controller?.systemBarsBehavior= WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY+ View.SYSTEM_UI_FLAG_FULLSCREEN+ View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController
            controller?.hide(WindowInsets.Type.statusBars() + WindowInsets.Type.navigationBars())
            controller?.systemBarsBehavior= WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY+View.SYSTEM_UI_FLAG_FULLSCREEN+View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }
}