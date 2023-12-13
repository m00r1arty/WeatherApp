package com.example.weatherapp.domain.mapper

import com.example.weatherapp.domain.model.CurrentWeatherCardModel
import com.example.weatherapp.domain.model.DaysWeatherItemModel
import com.example.weatherapp.data.model.response.WeatherResponse

fun WeatherResponse.toCurrentCardWeather() = CurrentWeatherCardModel(
    nameCity = this.location?.name,
    dateTime = this.location?.localtime,
    conditionText = this.current?.condition?.text,
    currentTemp = this.current?.tempC,
    maxTemp = this.forecast?.forecastDay?.get(0)?.day?.maxTempC,
    minTemp = this.forecast?.forecastDay?.get(0)?.day?.minTempC,
    imageUrl = this.current?.condition?.icon
)

fun WeatherResponse.toDaysItemWeatherList(): List<DaysWeatherItemModel>? {
    return this.forecast?.forecastDay?.drop(1)?.map {
        DaysWeatherItemModel(
            dateTime = it.date,
            conditionText = it.day?.condition?.text,
            avgTemp = it.day?.avgTempC,
            maxTemp = it.day?.maxTempC,
            minTemp = it.day?.minTempC,
            imageUrl = it.day?.condition?.icon
        )
    }
}
