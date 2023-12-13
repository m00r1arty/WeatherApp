package com.example.weatherapp.domain.model

data class DaysWeatherItemModel(
    val dateTime: String?,
    val conditionText: String?,
    val maxTemp: Float?,
    val minTemp: Float?,
    val imageUrl: String?,
)
