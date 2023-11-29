package com.example.weatherapp.domain

object Constants {
    // API ключ для доступа к погодному API
    const val API_KEY = "516e8d43fcac405c8a392705231411"

    // Базовый URL для погодного API
    const val BASE_URL = "https://api.weatherapi.com/v1/forecast.json?key="

    // Список типов прогноза времени
    val tList = listOf("Hours", "Days")
}
