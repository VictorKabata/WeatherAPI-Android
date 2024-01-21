package com.vickbt.weatherapiandroid.di

import com.vickbt.weatherapiandroid.ui.activities.main.MainViewModel
import com.vickbt.weatherapiandroid.ui.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
}
