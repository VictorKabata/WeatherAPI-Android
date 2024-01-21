package com.vickbt.weatherapiandroid.ui.activities.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.shared.domain.utils.Constants.THEME_KEY
import com.vickbt.shared.domain.utils.THEME_OPTIONS
import com.vickbt.shared.repository.datasource.WeatherRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val weatherRepository: WeatherRepositoryImpl) :
    ViewModel() {

    private val _themePreference = MutableStateFlow<THEME_OPTIONS?>(null)
    val themePreference get() = _themePreference.asStateFlow()

    init {
        getThemePreference()
    }

    fun getThemePreference() = viewModelScope.launch {
        weatherRepository.getThemeSettings().collect {
            _themePreference.value = THEME_OPTIONS.entries[it]
        }
    }

    fun saveThemePreference(selection: Int) = viewModelScope.launch {
        Log.e("VicKbt", "Theme selection: $selection")
        weatherRepository.saveSettings(key = THEME_KEY, selection = selection)
    }

}
