package com.example.weatherapp.domain.mainextensions

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.weatherapp.domain.mainextensions.ParsingData.requestWeatherData
import com.example.weatherapp.ui.DialogManager
import com.example.weatherapp.ui.fragments.MainFragment
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

object LocationManager {
    fun MainFragment.checkLocation() {
        if (isLocationEnabled()) {
            getLocation()
        } else {
            DialogManager.locationSettingsDialog(requireContext(), object : DialogManager.Listener {
                override fun onClick(name: String?) {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }
            })
        }
    }

    @SuppressLint("MissingPermission")
    private fun MainFragment.getLocation() {
        val cancelToken = CancellationTokenSource()
        if (isLocationPermissionGranted()) {
            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY,
                cancelToken.token).addOnCompleteListener { result ->
                    result.result?.let {
                        requestWeatherData("${it.latitude},${it.longitude}")
                    }
                }
        }
    }

    private fun MainFragment.isLocationPermissionGranted() =
        ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    private fun MainFragment.isLocationEnabled()  =
        (activity?.getSystemService(Context.LOCATION_SERVICE) as?
                LocationManager)?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true

    fun MainFragment.checkPermission() {
        if (!isLocationPermissionGranted()) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun MainFragment.permissionListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            val message = if (isGranted) "Permission granted!" else "Permission denied"
            Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
        }
    }

}