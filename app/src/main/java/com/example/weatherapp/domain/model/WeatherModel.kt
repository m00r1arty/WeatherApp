package com.example.weatherapp.domain.model

data class WeatherModel(
    val nameCity: String?, // location -> name
    val dateTime: String?, // (current -> last_updated), (forecast -> forecastday -> date)
    val conditionText: String?, // (current -> condition -> text), (forecast -> forecastday -> condition -> text)
    val currentTemp: Float?, // current -> temp_c
    val maxTemp: Float?, // forecast -> forecastday -> maxtemp_c
    val minTemp: Float?, // forecast -> forecastday -> mintemp_c
    val imageUrl: String?, // (current -> condition -> icon), (forecast -> forecastday -> condition -> icon)
    val hours: String, // forecast -> forecastday -> hours
)
