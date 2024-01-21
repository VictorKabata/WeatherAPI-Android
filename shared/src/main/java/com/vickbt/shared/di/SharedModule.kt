package com.vickbt.shared.di

import com.vickbt.shared.network.WeatherApiClient
import com.vickbt.shared.network.WeatherApiService
import io.github.aakira.napier.BuildConfig
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedModule: Module = module {

    /**Create a singleton instance of ktor client*/
    single { WeatherApiClient.createWeatherApiClient(enableNetworkLogs = BuildConfig.DEBUG) }

    single { WeatherApiService(weatherApiClient = get()) }
}
