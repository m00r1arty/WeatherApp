package com.example.weatherapp.data.repositories

import com.example.weatherapp.domain.model.CurrentWeatherCardModel
import com.example.weatherapp.data.network.NetworkService.Companion.handleCall
import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.data.model.response.WeatherResponse
import com.example.weatherapp.domain.mapper.toCurrentCardWeather
import com.example.weatherapp.domain.mapper.toDaysItemWeather
import com.example.weatherapp.domain.model.DaysWeatherItemModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {
    suspend fun getCurrentWeatherCard(
        apiKey: String,
        cityName: String,
    ): CurrentWeatherCardModel? {
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 1, "no", "no")
        val response: WeatherResponse? = handleCall(call)
        return response?.toCurrentCardWeather()
    }

    suspend fun getDaysItemWeather(
        apiKey: String,
        cityName: String,
    ): DaysWeatherItemModel? {
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 1, "no", "no")
        val response: WeatherResponse? = handleCall(call)
        return response?.toDaysItemWeather()
    }


}
