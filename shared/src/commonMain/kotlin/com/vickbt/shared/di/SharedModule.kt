package com.vickbt.shared.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.vickbt.shared.data.network.WeatherApiService
import com.vickbt.shared.data.repository.datasource.WeatherRepository
import io.github.aakira.napier.BuildConfig
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import utils.LocationService

val sharedModule: Module = module {

    /**Create a singleton instance of ktor client*/
    single { com.vickbt.shared.data.network.WeatherApiClient.createWeatherApiClient(enableNetworkLogs = BuildConfig.DEBUG) }

    /**Create a singleton of [WeatherApiService]*/
    singleOf(::WeatherApiService)

    /**Create a singleton of Observable Settings*/
    single { com.vickbt.shared.data.cache.MultiplatformSettingsWrapper.createSettings(context = get<Context>()) }

    singleOf(::WeatherRepository)

    /**Creates a fused location client used to create an instance of [LocationServices]*/
    single {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(get<Context>())
        LocationService(context = get(), locationClient = fusedLocationClient)
    }
}
