package com.example.weatherapp.domain.model.mapper

import com.example.weatherapp.domain.model.WeatherModel
import com.example.weatherapp.domain.model.WeatherResponse

fun WeatherResponse.toCurrentCardWeatherModel() = WeatherModel(
    nameCity = this.location?.name,
    dateTime = this.current?.lastUpdated,
    conditionText = this.current?.condition?.text,
    currentTemp = this.current?.tempC,
    maxTemp = null,
    minTemp = null,
    imageUrl = this.current?.condition?.icon,
    hours = this.forecast?.forecastDay?.get(0)?.hour.toString()
)


fun WeatherResponse.toDaysCardWeatherModel() = WeatherModel(
    nameCity = this.location?.name,
    dateTime = this.forecast?.forecastDay?.get(0)?.date,
    conditionText = this.forecast?.forecastDay?.get(0)?.day?.condition?.text,
    currentTemp = null,
    maxTemp = this.forecast?.forecastDay?.get(0)?.day?.maxtemp_c,
    minTemp = this.forecast?.forecastDay?.get(0)?.day?.mintemp_c,
    imageUrl = this.forecast?.forecastDay?.get(0)?.day?.condition?.icon,
    hours = ""
)

fun WeatherResponse.toHoursCardWeatherModel() = WeatherModel(
    nameCity = this.location?.name,
    dateTime = this.forecast?.forecastDay?.get(0)?.hour?.get(0)?.time,
    conditionText = this.forecast?.forecastDay?.get(0)?.hour?.get(0)?.condition?.text,
    currentTemp = this.forecast?.forecastDay?.get(0)?.hour?.get(0)?.tempC,
    maxTemp = null,
    minTemp = null,
    imageUrl = this.forecast?.forecastDay?.get(0)?.hour?.get(0)?.condition?.icon,
    hours = ""
)