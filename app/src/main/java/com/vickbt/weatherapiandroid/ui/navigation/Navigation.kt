package com.vickbt.weatherapiandroid.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vickbt.weatherapiandroid.ui.screens.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(route = NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }

        /*ToDo: Set settings screen
        composable(route = NavigationItem.Settings.route) {

        }*/
    }
}
