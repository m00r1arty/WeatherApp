package com.example.weatherapp.domain.model

import com.example.weatherapp.domain.model.responsebody.CurrentForecastResponse
import com.example.weatherapp.domain.model.responsebody.ForecastResponse
import com.example.weatherapp.domain.model.responsebody.LocationResponse
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location") val location: LocationResponse? = null,
    @SerializedName("current") val current: CurrentForecastResponse? = null,
    @SerializedName("forecast") val forecast: ForecastResponse? = null,
)