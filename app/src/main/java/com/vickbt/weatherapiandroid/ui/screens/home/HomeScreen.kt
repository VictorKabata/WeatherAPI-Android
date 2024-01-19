package com.vickbt.weatherapiandroid.ui.screens.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.vickbt.domain.utils.toReadableFormat
import com.vickbt.weatherapiandroid.R
import com.vickbt.weatherapiandroid.ui.components.ExtraCondition
import io.github.aakira.napier.Napier
import org.koin.compose.koinInject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koinInject()) {
    val homeUiState = viewModel.homeUiState.collectAsState().value

    Box(
        Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        if (homeUiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (homeUiState.currentWeather != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = 24.dp)
            ) {
                //region Location and Date Time
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = 2.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    Text(
                        text = "${homeUiState.currentWeather.location.name}," +
                                " ${homeUiState.currentWeather.location.country}",
                        fontWeight = FontWeight.Black,
                        fontSize = 24.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = homeUiState.currentWeather.location.localtime.toReadableFormat(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                //endregion

                //region Current Condition
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(120.dp),
                        painter = rememberImagePainter(R.drawable.weather_placeholder),
                        contentDescription = homeUiState.currentWeather.current.condition.text,
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = homeUiState.currentWeather.current.tempC.toInt().toString(),
                        fontSize = 80.sp,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 1
                    )

                    Text(
                        text = homeUiState.currentWeather.current.condition.text,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1
                    )
                }
                //endregion

                Divider(modifier = Modifier.padding(horizontal = 4.dp), thickness = 1.dp)

                //region Extra Conditions
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    item {
                        ExtraCondition(
                            icon = R.drawable.weather_placeholder,
                            title = R.string.humidity,
                            value = "${homeUiState.currentWeather.current.humidity}%"
                        )
                    }
                    item {
                        ExtraCondition(
                            icon = R.drawable.weather_placeholder,
                            title = R.string.feels_like,
                            value = "${homeUiState.currentWeather.current.tempC}"
                        )
                    }
                    item {
                        ExtraCondition(
                            icon = R.drawable.weather_placeholder,
                            title = R.string.wind,
                            value = "${homeUiState.currentWeather.current.windKph}km/h"
                        )
                    }
                    item {
                        ExtraCondition(
                            icon = R.drawable.weather_placeholder,
                            title = R.string.uv_index,
                            value = "${homeUiState.currentWeather.current.uv}"
                        )
                    }
                }
                //endregion

                Divider(modifier = Modifier.padding(horizontal = 4.dp), thickness = 1.dp)
            }
        } else if (homeUiState.error != null) {
            // ToDo: Display error
            val context = LocalContext.current

            Napier.e(tag = "VicKbt", message = "${homeUiState.error}")

            LaunchedEffect(key1 = Unit) {
                Toast.makeText(context, "Error: ${homeUiState.error}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    HomeScreen(navController = NavController(LocalContext.current))
}
