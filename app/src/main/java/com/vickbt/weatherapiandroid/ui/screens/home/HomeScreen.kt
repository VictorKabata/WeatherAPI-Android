package com.vickbt.weatherapiandroid.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import io.github.aakira.napier.Napier
import org.koin.compose.koinInject

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koinInject()) {

    LaunchedEffect(key1 = Unit) {
        Napier.e(tag = "VicKbt", message = "UI result: ${viewModel.homeUiState.value}")
    }

}