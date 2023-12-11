package com.example.weatherapp.domain.model

data class DaysWeatherItemModel(
    val dateTime: String?, // (current -> last_updated), (forecast -> forecastday -> date)
    val conditionText: String?, // (current -> condition -> text), (forecast -> forecastday -> condition -> text)
    val maxTemp: Float?, // forecast -> forecastday -> maxtemp_c
    val minTemp: Float?, // forecast -> forecastday -> mintemp_c
    val imageUrl: String?, // (current -> condition -> icon), (forecast -> forecastday -> condition -> icon)
)
