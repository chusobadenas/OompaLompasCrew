package com.jesusbadenas.oompaloompascrew.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jesusbadenas.oompaloompascrew.R
import com.jesusbadenas.oompaloompascrew.navigation.Navigator
import org.koin.android.ext.android.inject
import java.util.Timer
import java.util.TimerTask

class SplashActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        navigateToList()
    }

    private fun navigateToList() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                navigator.navigateToList(this@SplashActivity)
            }
        }, SPLASH_DURATION)
    }

    companion object {
        private const val SPLASH_DURATION = 1500L
    }
}
