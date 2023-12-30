package com.example.weatherapp.ui.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.weatherapp.ui.DialogManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class LocationManager(
    private val fusedLocationClient: FusedLocationProviderClient,
    private val fragment: Fragment
) {

    fun checkLocation(onLocationReceived: (Location) -> Unit) {
        if (isLocationEnabled()) {
            requireLocation { location ->
                location?.let { onLocationReceived(it) }
            }
        } else {
            DialogManager.locationSettingsDialog(fragment.requireContext()) {
                fragment.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun requireLocation(onComplete: (Location?) -> Unit = {}) {
        val cancelToken = CancellationTokenSource()
        if (isLocationPermissionGranted()) {
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancelToken.token
            ).addOnCompleteListener {
                onComplete(it.result)
            }
        }
    }

    fun isLocationPermissionGranted() =
        ContextCompat.checkSelfPermission(
            fragment.requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun isLocationEnabled() =
        (fragment.activity?.getSystemService(Context.LOCATION_SERVICE) as?
                LocationManager)?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true

}
