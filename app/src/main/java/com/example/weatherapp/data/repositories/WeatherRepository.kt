package com.example.weatherapp.data.repositories

import com.example.weatherapp.domain.model.CurrentWeatherCardModel
import com.example.weatherapp.data.network.NetworkService.Companion.handleCall
import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.data.model.response.WeatherResponse
import com.example.weatherapp.domain.mapper.toCurrentCardWeather
import com.example.weatherapp.domain.mapper.toCurrentCardWeatherFahrenheit
import com.example.weatherapp.domain.mapper.toDaysItemWeatherList
import com.example.weatherapp.domain.mapper.toDaysItemWeatherListFahrenheit
import com.example.weatherapp.domain.model.DaysWeatherItemModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {

    private val apiKey = "6183a5484fc64341bce122716231312"

    suspend fun getCurrentWeatherCard(
        cityName: String,
    ): CurrentWeatherCardModel? {
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 1, "no", "no")
        val response: WeatherResponse? = handleCall(call)
        return response?.toCurrentCardWeather()
    }

    suspend fun getDaysItemWeather(
        cityName: String,
    ): List<DaysWeatherItemModel>? {
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 6, "no", "no")
        val response: WeatherResponse? = handleCall(call)
        return response?.toDaysItemWeatherList()
    }

    suspend fun getCurrentWeatherCardFahrenheit(
        cityName: String,
    ): CurrentWeatherCardModel? {
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 1, "no", "no")
        val response: WeatherResponse? = handleCall(call)
        return response?.toCurrentCardWeatherFahrenheit()
    }

    suspend fun getDaysItemWeatherFahrenheit(
        cityName: String,
    ): List<DaysWeatherItemModel>? {
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 6, "no", "no")
        val response: WeatherResponse? = handleCall(call)
        return response?.toDaysItemWeatherListFahrenheit()
    }

}
