package com.vickbt.weatherapiandroid.ui.activities.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.shared.data.repository.datasource.WeatherRepositoryImpl
import com.vickbt.shared.domain.utils.Constants.MEASUREMENT_KEY
import com.vickbt.shared.domain.utils.Constants.THEME_KEY
import com.vickbt.shared.domain.utils.MEASUREMENT_OPTIONS
import com.vickbt.shared.domain.utils.THEME_OPTIONS
import com.vickbt.weatherapiandroid.utils.MainUiStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val weatherRepository: WeatherRepositoryImpl) :
    ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiStates())
    val mainUiState = _mainUiState.asStateFlow()

    init {
        getThemePreference()
        getMeasurementPreference()
    }

    fun getThemePreference() = viewModelScope.launch {
        weatherRepository.getThemeSettings().collect { themeIndex ->
            _mainUiState.update { it.copy(theme = THEME_OPTIONS.entries[themeIndex]) }
        }
    }

    fun getMeasurementPreference() = viewModelScope.launch {
        weatherRepository.getMeasurementSettings().collect { unitIndex ->
            _mainUiState.update { it.copy(unitOfMeasurement = MEASUREMENT_OPTIONS.entries[unitIndex]) }
        }
    }

    fun saveThemePreference(selection: Int) = viewModelScope.launch {
        weatherRepository.saveSettings(key = THEME_KEY, selection = selection)
    }

    fun saveMeasurementPreference(selection: Int) = viewModelScope.launch {
        weatherRepository.saveSettings(key = MEASUREMENT_KEY, selection = selection)
    }

}
