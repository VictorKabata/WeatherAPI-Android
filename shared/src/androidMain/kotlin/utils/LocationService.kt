package utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Looper
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**Util class to */
class LocationService(
    private val context: Context,
    private val locationClient: FusedLocationProviderClient
) {

    /**Returns user's current latitude and longitude*/
    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(): Flow<LatLng?> = callbackFlow {
        if (!context.areLocationPermissionsAlreadyGranted()) {
            trySend(null)
            return@callbackFlow
        }

        val request = LocationRequest.Builder(10000L)
            .setIntervalMillis(10000L)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.lastOrNull()?.let {
                    trySend(LatLng(it.latitude, it.longitude))
                }
            }
        }

        locationClient.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )

        awaitClose {
            locationClient.removeLocationUpdates(locationCallback)
        }
    }
}

/**Checks if location permissions have already been granted*/
fun Context.areLocationPermissionsAlreadyGranted(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

/**Opens application permission settings to request user to manually toggle location permission on*/
fun Context.openApplicationSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", "com.vickbt.weatherapiandroid", null)
    ).also {
        startActivity(it)
    }
}

/**Checks if permission has been granted or rejected/denied*/
fun decideCurrentPermissionStatus(
    locationPermissionsGranted: Boolean,
    shouldShowPermissionRationale: Boolean
): String {
    return if (locationPermissionsGranted) {
        "Granted"
    } else if (shouldShowPermissionRationale) {
        "Rejected"
    } else {
        "Denied"
    }
}
