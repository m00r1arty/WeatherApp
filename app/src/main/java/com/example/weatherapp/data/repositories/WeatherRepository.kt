package com.example.weatherapp.data.repositories

import com.example.weatherapp.domain.model.CurrentWeatherCardModel
import com.example.weatherapp.data.network.NetworkService.Companion.handleCall
import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.data.model.response.WeatherResponse
import com.example.weatherapp.domain.mapper.toCurrentCardWeather
import com.example.weatherapp.domain.mapper.toDaysItemWeatherList
import com.example.weatherapp.domain.model.DaysWeatherItemModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {

    suspend fun getCurrentWeatherCard(
        cityName: String,
    ): CurrentWeatherCardModel? {
        val call = weatherApi.getWeatherForecast(API_KEY, cityName, 1, AQI, ALERTS)
        val response: WeatherResponse? = handleCall(call)
        return response?.toCurrentCardWeather()
    }

    suspend fun getDaysItemWeather(
        cityName: String,
    ): List<DaysWeatherItemModel>? {
        val call = weatherApi.getWeatherForecast(API_KEY, cityName, 6, AQI, ALERTS)
        val response: WeatherResponse? = handleCall(call)
        return response?.toDaysItemWeatherList()
    }

    companion object {
        private const val API_KEY = "6183a5484fc64341bce122716231312"
        private const val AQI = "no"
        private const val ALERTS = "no"
    }

}
