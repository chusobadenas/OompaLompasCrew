package com.jesusbadenas.oompaloompascrew

import android.app.Application
import com.jesusbadenas.oompaloompascrew.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appComponent)
        }
    }
}
