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

fun WeatherResponse.toDaysItemWeather() = DaysWeatherItemModel(
    dateTime = this.forecast?.forecastDay?.get(0)?.date,
    conditionText = this.forecast?.forecastDay?.get(0)?.day?.condition?.text,
    avgTemp = this.forecast?.forecastDay?.get(0)?.day?.avgTempC,
    maxTemp = this.forecast?.forecastDay?.get(0)?.day?.maxTempC,
    minTemp = this.forecast?.forecastDay?.get(0)?.day?.minTempC,
    imageUrl = this.forecast?.forecastDay?.get(0)?.day?.condition?.icon,
)