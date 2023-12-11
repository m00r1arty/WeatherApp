package com.example.weatherapp.domain.model.responsebody

import com.google.gson.annotations.SerializedName

data class ForecastDayResponse(
    @SerializedName("date") val date: String? = null,
    @SerializedName("date_epoch") val dateEpoch: Long? = null,
    @SerializedName("day") val day: DayResponse? = null,
    @SerializedName("astro") val astro: AstroResponse? = null,
    @SerializedName("hour") val hour: List<HourResponse>? = null
)
