package com.manasmalla.ahamsvastha

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity


class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler: Looper
    lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController
            controller?.hide(WindowInsets.Type.statusBars() + WindowInsets.Type.navigationBars())
            controller?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY + View.SYSTEM_UI_FLAG_FULLSCREEN + View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController
            controller?.hide(WindowInsets.Type.statusBars() + WindowInsets.Type.navigationBars())
            controller?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY + View.SYSTEM_UI_FLAG_FULLSCREEN + View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
        handler = Looper.getMainLooper()
        runnable = Runnable {
            if (AhamSvasthaUser().getAhamSvasthaUserNumber(applicationContext) > 0) {
                if (!AhamSvasthaUser().getCurrentUser(applicationContext).equals("")) {
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    AhamSvasthaUser().getAhamSvasthaUserList(applicationContext).elementAt(0)
                }
            } else {
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        Handler(handler).postDelayed(runnable, 2500)
    }
}