package com.example.weatherapp.data.repositories

import com.example.weatherapp.domain.model.CurrentWeatherCardModel
import com.example.weatherapp.data.network.NetworkService.Companion.handleCall
import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.data.model.response.WeatherResponse
import com.example.weatherapp.domain.mapper.toCurrentCardWeather
import com.example.weatherapp.domain.mapper.toDaysCardWeather
import com.example.weatherapp.domain.mapper.toDaysItemWeather
import com.example.weatherapp.domain.mapper.toHoursItemWeather
import com.example.weatherapp.domain.model.DaysWeatherCardModel
import com.example.weatherapp.domain.model.DaysWeatherItemModel
import com.example.weatherapp.domain.model.HoursWeatherItemModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {
    suspend fun getCurrentWeatherCard(
        apiKey: String,
        cityName: String,
    ): CurrentWeatherCardModel {
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 1, "no", "no")
        val response: WeatherResponse? = handleCall(call)
        return response!!.toCurrentCardWeather()
    }

    suspend fun getDaysCardWeather(
        apiKey: String,
        cityName: String,
    ): DaysWeatherCardModel {
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 1, "no", "no")
        val response: WeatherResponse? = handleCall(call)
        return response!!.toDaysCardWeather()
    }

    suspend fun getDaysItemWeather(
        apiKey: String,
        cityName: String,
    ): DaysWeatherItemModel {
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 1, "no", "no")
        val response: WeatherResponse? = handleCall(call)
        return response!!.toDaysItemWeather()
    }

    suspend fun getHoursItemWeather(
        apiKey: String,
        cityName: String,
    ): HoursWeatherItemModel {
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 1, "no", "no")
        val response: WeatherResponse? = handleCall(call)
        return response!!.toHoursItemWeather()
    }

}
