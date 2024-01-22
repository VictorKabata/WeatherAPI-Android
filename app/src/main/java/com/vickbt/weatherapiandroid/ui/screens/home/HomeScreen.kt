package com.vickbt.weatherapiandroid.ui.screens.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.vickbt.shared.domain.utils.toImageFormat
import com.vickbt.shared.domain.utils.toReadableFormat
import com.vickbt.shared.domain.utils.toSpeedUnitOfMeasurement
import com.vickbt.shared.domain.utils.toTempUnitOfMeasurement
import com.vickbt.weatherapiandroid.R
import com.vickbt.weatherapiandroid.ui.components.DayCondition
import com.vickbt.weatherapiandroid.ui.components.ExtraCondition
import org.koin.compose.koinInject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = koinInject()
) {
    val homeUiState = viewModel.homeUiState.collectAsState().value
    val scrollState = rememberScrollState()

    val unitOfMeasurement by remember { mutableStateOf(homeUiState.unitOfMeasurement) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        if (homeUiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .testTag("loading_progress_bar")
                    .align(Alignment.Center)
            )
        } else if (homeUiState.forecastWeather != null) {
            Column(
                modifier = Modifier
                    .testTag("weather_info_column")
                    .fillMaxSize()
                    .padding(paddingValues)
                    .align(Alignment.Center)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(space = 12.dp)
            ) {
                //region Location and Date Time
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = 2.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    Text(
                        modifier = Modifier.testTag("location_text"),
                        text = "${homeUiState.forecastWeather.location.name}," +
                                " ${homeUiState.forecastWeather.location.country}",
                        fontWeight = FontWeight.Black,
                        fontSize = 24.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        modifier = Modifier.testTag("date_text"),
                        text = homeUiState.forecastWeather.location.localtime.toReadableFormat(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                //endregion

                //region Current Condition
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(150.dp),
                        painter = rememberImagePainter(
                            homeUiState.forecastWeather.current.condition.icon.toImageFormat()
                        ),
                        contentDescription = homeUiState.forecastWeather.current.condition.text,
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = homeUiState.forecastWeather.current.temp
                            .toTempUnitOfMeasurement(unitOfMeasurement = unitOfMeasurement),
                        fontSize = 80.sp,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 1
                    )

                    Text(
                        text = homeUiState.forecastWeather.current.condition.text,
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
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    item {
                        ExtraCondition(
                            icon = R.drawable.humidity_percentage,
                            title = R.string.humidity,
                            value = "${homeUiState.forecastWeather.current.humidity}%"
                        )
                    }
                    item {
                        ExtraCondition(
                            icon = R.drawable.thermometer,
                            title = R.string.feels_like,
                            value = homeUiState.forecastWeather.current.temp
                                .toTempUnitOfMeasurement(unitOfMeasurement = unitOfMeasurement)
                        )
                    }
                    item {
                        ExtraCondition(
                            icon = R.drawable.wind,
                            title = R.string.wind,
                            value = homeUiState.forecastWeather.current.wind
                                .toSpeedUnitOfMeasurement(unitOfMeasurement = unitOfMeasurement)
                        )
                    }
                    item {
                        ExtraCondition(
                            icon = R.drawable.uv_index,
                            title = R.string.uv_index,
                            value = "${homeUiState.forecastWeather.current.uv}"
                        )
                    }
                }
                //endregion

                Divider(modifier = Modifier.padding(horizontal = 4.dp), thickness = 1.dp)

                //region Weekly Forecast
                Text(text = "This Week", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(items = homeUiState.forecastWeather.forecast) {
                        DayCondition(
                            modifier = Modifier.size(90.dp),
                            imageUrl = it.day.condition.icon.toImageFormat(),
                            dayOfWeek = it.dateEpoch.dayOfWeek.toString().uppercase(),
                            minTemp = it.day.mintemp.toTempUnitOfMeasurement(unitOfMeasurement = unitOfMeasurement),
                            maxTemp = it.day.maxtemp.toTempUnitOfMeasurement(unitOfMeasurement = unitOfMeasurement)
                        )
                    }
                }
                //endregion

                Divider(modifier = Modifier.padding(horizontal = 4.dp), thickness = 1.dp)

                //region Historical Forecast
                Text(text = "Past 2 Week", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(items = homeUiState.historyWeather?.forecast?.reversed() ?: emptyList()) {
                        DayCondition(
                            modifier = Modifier
                                .width(90.dp)
                                .height(120.dp),
                            imageUrl = it.day.condition.icon.toImageFormat(),
                            dayOfWeek = it.dateEpoch.dayOfWeek.toString().uppercase(),
                            dateOfMonth = it.dateEpoch.dayOfMonth.toString(),
                            minTemp = it.day.mintemp.toTempUnitOfMeasurement(unitOfMeasurement = unitOfMeasurement),
                            maxTemp = it.day.maxtemp.toTempUnitOfMeasurement(unitOfMeasurement = unitOfMeasurement)
                        )
                    }
                }
                //endregion
            }
        } else if (homeUiState.error != null) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 24.dp),
                text = homeUiState.error,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}
