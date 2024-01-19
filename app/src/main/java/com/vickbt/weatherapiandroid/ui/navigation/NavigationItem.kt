package com.vickbt.weatherapiandroid.ui.navigation

import androidx.annotation.StringRes
import com.vickbt.weatherapiandroid.R

sealed class NavigationItem(val route: String, @StringRes val title: Int) {
    data object Home : NavigationItem(route = "home", title = R.string.title_home)
    data object Settings : NavigationItem(route = "settings", title = R.string.title_settings)
}
