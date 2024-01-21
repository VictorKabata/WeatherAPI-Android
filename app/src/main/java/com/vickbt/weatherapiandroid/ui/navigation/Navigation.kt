package com.vickbt.weatherapiandroid.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vickbt.weatherapiandroid.ui.screens.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(route = NavigationItem.Home.route) {
            HomeScreen(navController = navController, paddingValues = paddingValues)
        }

        /*ToDo: Set settings screen
        composable(route = NavigationItem.Settings.route) {

        }*/
    }
}
