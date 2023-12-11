package com.example.weatherapp.domain.mapper

import com.example.weatherapp.domain.model.CurrentWeatherCardModel
import com.example.weatherapp.domain.model.DaysWeatherCardModel
import com.example.weatherapp.domain.model.DaysWeatherItemModel
import com.example.weatherapp.domain.model.HoursWeatherItemModel
import com.example.weatherapp.data.model.response.WeatherResponse

fun WeatherResponse.toCurrentCardWeather() = CurrentWeatherCardModel(
    nameCity = this.location?.name,
    dateTime = this.forecast?.forecastDay?.get(0)?.date,
    conditionText = this.current?.condition?.text,
    currentTemp = this.current?.tempC,
    maxTemp = this.forecast?.forecastDay?.get(0)?.day?.maxtemp_c,
    minTemp = this.forecast?.forecastDay?.get(0)?.day?.mintemp_c,
    imageUrl = this.current?.condition?.icon
)

fun WeatherResponse.toDaysCardWeather() = DaysWeatherCardModel(
    nameCity = this.location?.name,
    date = this.forecast?.forecastDay?.get(0)?.date,
    conditionText = this.forecast?.forecastDay?.get(0)?.day?.condition?.text,
    maxTemp = this.forecast?.forecastDay?.get(0)?.day?.maxtemp_c,
    minTemp = this.forecast?.forecastDay?.get(0)?.day?.mintemp_c,
    imageUrl = this.forecast?.forecastDay?.get(0)?.day?.condition?.icon,
)

fun WeatherResponse.toDaysItemWeather() = DaysWeatherItemModel(
    dateTime = this.forecast?.forecastDay?.get(0)?.date,
    conditionText = this.forecast?.forecastDay?.get(0)?.day?.condition?.text,
    maxTemp = this.forecast?.forecastDay?.get(0)?.day?.maxtemp_c,
    minTemp = this.forecast?.forecastDay?.get(0)?.day?.mintemp_c,
    imageUrl = this.forecast?.forecastDay?.get(0)?.day?.condition?.icon,
)

fun WeatherResponse.toHoursItemWeather() = HoursWeatherItemModel(
    time = this.forecast?.forecastDay?.get(0)?.hour?.get(0)?.time,
    conditionText = this.forecast?.forecastDay?.get(0)?.hour?.get(0)?.condition?.text,
    currentTemp = this.forecast?.forecastDay?.get(0)?.hour?.get(0)?.tempC,
    imageUrl = this.forecast?.forecastDay?.get(0)?.hour?.get(0)?.condition?.icon,
)