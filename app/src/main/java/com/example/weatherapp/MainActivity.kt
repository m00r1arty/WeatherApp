package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.weatherapp.data.repositories.WeatherRepository

class MainActivity : AppCompatActivity() {
    private val weatherRepository = WeatherRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiKey = "516e8d43fcac405c8a392705231411"
        val cityName = "Kirov"

        weatherRepository.getWeather(apiKey, cityName,
            onResponse = { weatherModel ->
                // Обработка данных о погоде
                Log.e("LOGGGG", weatherModel.toString())
            },
            onFailure = { errorMessage ->
                // Обработка ошибок
                Log.e("LOGGGG", errorMessage)
            }
        )

    }
}