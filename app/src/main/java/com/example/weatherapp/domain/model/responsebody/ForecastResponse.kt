package com.example.weatherapp.domain.model.responsebody

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("forecastday") val forecastDay: List<ForecastDayResponse>? = null
)