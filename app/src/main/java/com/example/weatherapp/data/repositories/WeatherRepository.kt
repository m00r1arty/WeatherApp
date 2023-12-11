package com.example.weatherapp.data.repositories

import com.example.weatherapp.domain.model.CurrentWeatherCardModel
import com.example.weatherapp.data.network.NetworkService.Companion.handleCall
import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.data.model.response.WeatherResponse
import com.example.weatherapp.domain.mapper.toCurrentCardWeather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {
    suspend fun getWeather(
        apiKey: String,
        cityName: String,
    ): CurrentWeatherCardModel {
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 1, "no", "no")
        val response: WeatherResponse? = handleCall(call)
        return response!!.toCurrentCardWeather()
    }

}
