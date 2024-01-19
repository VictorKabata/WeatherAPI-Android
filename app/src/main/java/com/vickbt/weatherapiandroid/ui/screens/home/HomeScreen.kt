package com.vickbt.weatherapiandroid.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.aakira.napier.Napier
import org.koin.compose.koinInject

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koinInject()) {

    val homeUiState by remember { mutableStateOf(viewModel.homeUiState.value) }
    Napier.e(tag = "VicKbt", message = "Result in UI: $homeUiState")

    Column(
        Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //region Location and Date Time
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text(
                text = "${homeUiState.currentWeather?.location?.name ?: ""}," +
                        " ${homeUiState.currentWeather?.location?.country ?: ""}",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Friday, 19 November 1998",
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        //endregion
    }
}

@Preview
@Composable
fun Preview() {

}