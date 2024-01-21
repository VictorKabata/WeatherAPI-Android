package com.vickbt.weatherapiandroid

import android.app.Application
import com.vickbt.repository.di.repositoryModule
import com.vickbt.shared.di.sharedModule
import com.vickbt.weatherapiandroid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WeatherApiAndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = listOf(sharedModule, repositoryModule, appModule)
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@WeatherApiAndroidApplication)
            modules(modules)
        }
    }
}
