package com.vickbt.shared.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.vickbt.shared.network.WeatherApiClient
import com.vickbt.shared.network.WeatherApiService
import com.vickbt.shared.repository.datasource.WeatherRepositoryImpl
import io.github.aakira.napier.BuildConfig
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import utils.LocationService

val sharedModule: Module = module {

    /**Create a singleton instance of ktor client*/
    single { WeatherApiClient.createWeatherApiClient(enableNetworkLogs = BuildConfig.DEBUG) }

    singleOf(::WeatherApiService)

    singleOf(::WeatherRepositoryImpl)

    single {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(get<Context>())
        LocationService(context = get(), locationClient = fusedLocationClient)
    }
}
