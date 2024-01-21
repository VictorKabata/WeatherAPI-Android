package com.vickbt.repository.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.vickbt.repository.datasource.WeatherRepositoryImpl
import com.vickbt.repository.utils.LocationService
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule: Module = module {
    singleOf(::WeatherRepositoryImpl)

    single {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(get<Context>())
        LocationService(context = get(), locationClient = fusedLocationClient)
    }
}
