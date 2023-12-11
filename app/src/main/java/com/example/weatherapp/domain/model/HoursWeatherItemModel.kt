package com.example.weatherapp.domain.model

data class HoursWeatherItemModel(
    val time: String?, // (current -> last_updated), (forecast -> forecastday -> date)
    val conditionText: String?, // (current -> condition -> text), (forecast -> forecastday -> condition -> text)
    val currentTemp: Float?, // current -> temp_c
    val imageUrl: String?, // (current -> condition -> icon), (forecast -> forecastday -> condition -> icon)
)