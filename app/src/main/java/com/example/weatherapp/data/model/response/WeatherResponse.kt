package com.example.weatherapp.data.model.response

import com.example.weatherapp.data.model.response.responsebody.CurrentForecastResponse
import com.example.weatherapp.data.model.response.responsebody.ForecastResponse
import com.example.weatherapp.data.model.response.responsebody.LocationResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Call

data class WeatherResponse(
    @SerializedName("location") val location: LocationResponse? = null,
    @SerializedName("current") val current: CurrentForecastResponse? = null,
    @SerializedName("forecast") val forecast: ForecastResponse? = null,
)