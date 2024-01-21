package com.vickbt.weatherapiandroid.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.compose.rememberNavController
import com.vickbt.repository.utils.areLocationPermissionsAlreadyGranted
import com.vickbt.repository.utils.decideCurrentPermissionStatus
import com.vickbt.repository.utils.openApplicationSettings
import com.vickbt.weatherapiandroid.R
import com.vickbt.weatherapiandroid.ui.components.NavigationDrawerContent
import com.vickbt.weatherapiandroid.ui.navigation.Navigation
import com.vickbt.weatherapiandroid.ui.theme.WeatherAPIAndroidTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var locationPermissionsGranted by remember {
                mutableStateOf(
                    areLocationPermissionsAlreadyGranted()
                )
            }
            var shouldShowPermissionRationale by remember {
                mutableStateOf(
                    shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
                )
            }

            var shouldDirectUserToApplicationSettings by remember { mutableStateOf(false) }

            var currentPermissionsStatus by remember {
                mutableStateOf(
                    decideCurrentPermissionStatus(
                        locationPermissionsGranted,
                        shouldShowPermissionRationale
                    )
                )
            }

            val locationPermissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

            val locationPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions(),
                onResult = { permissions ->
                    locationPermissionsGranted =
                        permissions.values.reduce { acc, isPermissionGranted ->
                            acc && isPermissionGranted
                        }

                    if (!locationPermissionsGranted) {
                        shouldShowPermissionRationale =
                            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
                    }
                    shouldDirectUserToApplicationSettings =
                        !shouldShowPermissionRationale && !locationPermissionsGranted
                    currentPermissionsStatus = decideCurrentPermissionStatus(
                        locationPermissionsGranted,
                        shouldShowPermissionRationale
                    )
                }
            )

            val lifecycleOwner = LocalLifecycleOwner.current
            DisposableEffect(
                key1 = lifecycleOwner,
                effect = {
                    val observer = LifecycleEventObserver { _, event ->
                        if (event == Lifecycle.Event.ON_START &&
                            !locationPermissionsGranted &&
                            !shouldShowPermissionRationale
                        ) {
                            locationPermissionLauncher.launch(locationPermissions)
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }
            )

            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

            WeatherAPIAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ModalNavigationDrawer(
                        drawerContent = {
                            NavigationDrawerContent(
                                modifier = Modifier,
                                locationQuery = "",
                                locationQueryChange = {},
                                onLocationQueried = { },
                                isThemeCheckedOn = false,
                                onThemeCheckChanged = {},
                                isImperialCheckedOn = false,
                                onImperialCheckChanged = {}
                            )
                        },
                        drawerState = drawerState
                    ) {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            topBar = {
                                TopAppBar(title = {}, navigationIcon = {
                                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                        Icon(
                                            imageVector = Icons.Rounded.Menu,
                                            contentDescription = getString(R.string.menu)
                                        )
                                    }
                                })
                            },
                            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                        ) { paddingValues ->
                            if (shouldShowPermissionRationale) {
                                LaunchedEffect(Unit) {
                                    scope.launch {
                                        val userAction = snackbarHostState.showSnackbar(
                                            message = getString(R.string.permission_request_message),
                                            actionLabel = getString(R.string.request),
                                            duration = SnackbarDuration.Indefinite,
                                            withDismissAction = false
                                        )
                                        when (userAction) {
                                            SnackbarResult.ActionPerformed -> {
                                                shouldShowPermissionRationale = false
                                                locationPermissionLauncher.launch(
                                                    locationPermissions
                                                )
                                            }

                                            SnackbarResult.Dismissed -> {
                                                shouldShowPermissionRationale = false
                                            }
                                        }
                                    }
                                }
                            }
                            if (shouldDirectUserToApplicationSettings) {
                                openApplicationSettings()
                            }

                            if (locationPermissionsGranted) {
                                val navController = rememberNavController()
                                Navigation(
                                    navController = navController,
                                    paddingValues = paddingValues
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    WeatherAPIAndroidTheme {
    }
}
