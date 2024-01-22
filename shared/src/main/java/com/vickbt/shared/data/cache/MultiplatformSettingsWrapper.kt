package com.vickbt.shared.data.cache

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

object MultiplatformSettingsWrapper {

    /**Creates an instance of [ObservableSettings] used to access methods to save key-value pairs*/
    fun createSettings(context: Context): ObservableSettings {
        val sharedPreferences =
            context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(delegate = sharedPreferences)
    }
}
