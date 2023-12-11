package com.example.weatherapp.data.model.response.responsebody

import com.google.gson.annotations.SerializedName

data class CurrentForecastResponse(
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Long? = null,
    @SerializedName("last_updated") val lastUpdated: String? = null,
    @SerializedName("temp_c") val tempC: Float? = null,
    @SerializedName("temp_f") private val tempF: Float? = null,
    @SerializedName("is_day") private val isDay: Float? = null,
    @SerializedName("condition") val condition: Condition? = null,
    @SerializedName("wind_mph") private val windMph: Float? = null,
    @SerializedName("wind_kph") private val windKph: Float? = null,
    @SerializedName("wind_degree") private val windDegree: Float? = null,
    @SerializedName("wind_dir") private val windDir: String? = null,
    @SerializedName("pressure_mb") private val pressureMb: Float? = null,
    @SerializedName("pressure_in") val pressureIn: Float? = null,
    @SerializedName("precip_mm") val precipMm: Float? = null,
    @SerializedName("precip_in") val precipIn: Float? = null,
    @SerializedName("humidity") val humidity: Long? = null,
    @SerializedName("cloud") val cloud: Long? = null,
    @SerializedName("feelslike_c") val feelslikeС: Float? = null,
    @SerializedName("feelslike_f") val feelslikeF: Float? = null,
    @SerializedName("vis_km") val visKm: Float? = null,
    @SerializedName("vis_miles") val visMiles: Float? = null,
    @SerializedName("uv") val uv: Float? = null,
    @SerializedName("gust_mph") val gustMph: Float? = null,
    @SerializedName("gust_kph") val gustKph: Float? = null,
)
