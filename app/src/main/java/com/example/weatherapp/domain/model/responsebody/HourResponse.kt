package com.example.weatherapp.domain.model.responsebody

import com.google.gson.annotations.SerializedName

data class HourResponse(
    @SerializedName("time_epoch") val timeEpoch: Long? = null,
    @SerializedName("time") val time: String? = null,
    @SerializedName("temp_c") val tempC: Float? = null,
    @SerializedName("temp_f") val tempF: Float? = null,
    @SerializedName("is_day") val isDay: Long? = null,
    @SerializedName("condition") val condition: Condition? = null,
    @SerializedName("wind_mph") val windMph: Float? = null,
    @SerializedName("wind_kph") val windKph: Float? = null,
    @SerializedName("wind_degree") val windDegree: Long? = null,
    @SerializedName("wind_dir") val windDir: String? = null,
    @SerializedName("pressure_mb") val pressureMb: Float? = null,
    @SerializedName("pressure_in") val pressureIn: Float? = null,
    @SerializedName("precip_mm") val precipMm: Float? = null,
    @SerializedName("precip_in") val precipIn: Float? = null,
    @SerializedName("humidity") val humidity: Long? = null,
    @SerializedName("cloud") val cloud: Long? = null,
    @SerializedName("feelslike_c") val feelslikeC: Float? = null,
    @SerializedName("feelslike_f") val feelslikeF: Float? = null,
    @SerializedName("windchill_c") val windchillC: Float? = null,
    @SerializedName("windchill_f") val windchillF: Float? = null,
    @SerializedName("heatindex_c") val heatindexC: Float? = null,
    @SerializedName("heatindex_f") val heatindexF: Float? = null,
    @SerializedName("dewpoint_c") val dewpointC: Float? = null,
    @SerializedName("dewpoint_f") val dewpointF: Float? = null,
    @SerializedName("will_it_rain") val willItRain: Long? = null,
    @SerializedName("chance_of_rain") val chanceOfRain: Long? = null,
    @SerializedName("will_it_snow") val willItSnow: Long? = null,
    @SerializedName("chance_of_snow") val chanceOfSnow: Long? = null,
    @SerializedName("vis_km") val visKm: Float? = null,
    @SerializedName("vis_miles") val visMiles: Float? = null,
    @SerializedName("gust_mph") val gustMph: Float? = null,
    @SerializedName("gust_kph") val gustKph: Float? = null,
    @SerializedName("uv") val uv: Float? = null
)