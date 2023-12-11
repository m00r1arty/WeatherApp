package com.example.weatherapp.data.network

import com.example.weatherapp.domain.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")
    fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") cityName: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Call<WeatherResponse>
}
