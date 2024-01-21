package com.vickbt.shared.cache

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

object MultiplatformSettingsWrapper {

    fun createSettings(context: Context): ObservableSettings {
        val sharedPreferences =
            context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(delegate = sharedPreferences)
    }
}
