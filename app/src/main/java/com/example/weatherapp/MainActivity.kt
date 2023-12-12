package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.data.network.NetworkService
import com.example.weatherapp.data.repositories.WeatherRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val weatherRepository = WeatherRepository(NetworkService.getInstance().weatherApi)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiKey = "516e8d43fcac405c8a392705231411"
        val cityName = "Kirov-Chepetsk"

        lifecycleScope.launch {
            val currentCardWeatherModel = weatherRepository.getCurrentWeatherCard(apiKey, cityName)
            val daysCardWeatherModel = weatherRepository.getDaysCardWeather(apiKey, cityName)
            val daysItemWeatherModel = weatherRepository.getDaysItemWeather(apiKey, cityName)
            val hoursItemWeatherModel = weatherRepository.getHoursItemWeather(apiKey, cityName)
            Log.e("LOGGGG", currentCardWeatherModel.toString())
            Log.e("LOGGGG", daysCardWeatherModel.toString())
            Log.e("LOGGGG", daysItemWeatherModel.toString())
            Log.e("LOGGGG", hoursItemWeatherModel.toString())
        }

    }
}