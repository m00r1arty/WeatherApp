package com.example.weatherapp.domain.mapper

import com.example.weatherapp.domain.model.CurrentWeatherCardModel
import com.example.weatherapp.domain.model.DaysWeatherItemModel
import com.example.weatherapp.data.model.response.WeatherResponse
import kotlin.math.floor

fun WeatherResponse.toCurrentCardWeather() = CurrentWeatherCardModel(
    nameCity = this.location?.name,
    dateTime = this.location?.localtime,
    conditionText = this.current?.condition?.text,
    currentTemp = this.current?.tempC?.let { it1 -> floor(it1) },
    maxTemp = this.forecast?.forecastDay?.first()?.day?.maxTempC?.let { it1 -> floor(it1) },
    minTemp = this.forecast?.forecastDay?.first()?.day?.minTempC?.let { it1 -> floor(it1) },
    imageUrl = this.current?.condition?.icon
)

fun WeatherResponse.toDaysItemWeatherList(): List<DaysWeatherItemModel>? {
    return this.forecast?.forecastDay?.drop(1)?.map {
        DaysWeatherItemModel(
            dateTime = it.date,
            conditionText = it.day?.condition?.text,
            maxTemp = it.day?.maxTempC?.let { it1 -> floor(it1) },
            minTemp = it.day?.minTempC?.let { it1 -> floor(it1) },
            imageUrl = it.day?.condition?.icon
        )
    }
}

