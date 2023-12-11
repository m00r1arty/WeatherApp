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
import com.example.weatherapp.ui.DialogManager
import com.example.weatherapp.ui.fragments.MainFragment
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
//
//object LocationManager {
//    /**
//     * Проверяет доступность местоположения и инициирует запрос на получение координат.
//     */
//    fun MainFragment.checkLocation() {
//        if (isLocationEnabled()) {
//            getLocation()
//        } else {
//            DialogManager.locationSettingsDialog(requireContext()) {
//                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
//            }
//        }
//    }
//
//    /**
//     * Получает текущие координаты пользователя и запрашивает данные о погоде.
//     */
//    @SuppressLint("MissingPermission")
//    private fun MainFragment.getLocation() {
//        val cancelToken = CancellationTokenSource()
//        if (isLocationPermissionGranted()) {
//            fusedLocationClient.getCurrentLocation(
//                Priority.PRIORITY_HIGH_ACCURACY,
//                cancelToken.token
//            ).addOnCompleteListener { result ->
//                result.result?.let {
//                    // Запрос данных о погоде на основе координат
//                    requestWeatherData("${it.latitude},${it.longitude}")
//                }
//            }
//        }
//    }
//
//    /**
//     * Проверяет, предоставлено ли разрешение на использование местоположения.
//     */
//    private fun MainFragment.isLocationPermissionGranted() =
//        ContextCompat.checkSelfPermission(
//            requireContext(),
//            Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED
//
//    /**
//     * Проверяет, включена ли функция определения местоположения на устройстве.
//     */
//    private fun MainFragment.isLocationEnabled() =
//        (activity?.getSystemService(Context.LOCATION_SERVICE) as?
//                LocationManager)?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true
//
//    /**
//     * Проверяет наличие разрешения на использование местоположения и запрашивает его при необходимости.
//     */
//    fun MainFragment.checkPermission() {
//        if (!isLocationPermissionGranted()) {
//            // Запуск запроса разрешения на использование местоположения
//            permissionListener()
//            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//        }
//    }
//
//    /**
//     * Обработчик результата запроса разрешения на использование местоположения.
//     */
//    private fun MainFragment.permissionListener() {
//        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
//            val message = if (isGranted) "Permission granted!" else "Permission denied"
//            Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
//        }
//    }
//}
