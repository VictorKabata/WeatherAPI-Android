package com.vickbt.network.di

import com.vickbt.network.WeatherApiClient
import com.vickbt.network.WeatherApiService
import io.github.aakira.napier.BuildConfig
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module = module {

    /**Create a singleton instance of ktor client*/
    single { WeatherApiClient.createWeatherApiClient(enableNetworkLogs = BuildConfig.DEBUG) }

    single { WeatherApiService(weatherApiClient = get()) }
}