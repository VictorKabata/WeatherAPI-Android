package com.vickbt.repository.di

import com.vickbt.repository.datasource.WeatherRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module= module {
    single { WeatherRepositoryImpl(weatherApiService = get()) }
}