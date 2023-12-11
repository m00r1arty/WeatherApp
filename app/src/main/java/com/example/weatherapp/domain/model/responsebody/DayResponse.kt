package com.example.weatherapp.domain.model.responsebody

import com.google.gson.annotations.SerializedName

data class DayResponse(
    @SerializedName("maxtemp_c") val maxtemp_c: Float? = null,
    @SerializedName("maxtemp_f") val maxtemp_f: Float? = null,
    @SerializedName("mintemp_c") val mintemp_c: Float? = null,
    @SerializedName("mintemp_f") val mintemp_f: Float? = null,
    @SerializedName("avgtemp_c") val avgtemp_c: Float? = null,
    @SerializedName("avgtemp_f") val avgtemp_f: Float? = null,
    @SerializedName("maxwind_mph") val maxwind_mph: Float? = null,
    @SerializedName("maxwind_kph") val maxwind_kph: Float? = null,
    @SerializedName("totalprecip_mm") val totalprecip_mm: Float? = null,
    @SerializedName("totalprecip_in") val totalprecip_in: Float? = null,
    @SerializedName("totalsnow_cm") val totalsnow_cm: Float? = null,
    @SerializedName("avgvis_km") val avgvis_km: Float? = null,
    @SerializedName("avgvis_miles") val avgvis_miles: Float? = null,
    @SerializedName("avghumidity") val avghumidity: Float? = null,
    @SerializedName("daily_will_it_rain") val daily_will_it_rain: Int? = null,
    @SerializedName("daily_chance_of_rain") val daily_chance_of_rain: Int? = null,
    @SerializedName("daily_will_it_snow") val daily_will_it_snow: Int? = null,
    @SerializedName("daily_chance_of_snow") val daily_chance_of_snow: Int? = null,
    @SerializedName("condition") val condition: Condition? = null,
    @SerializedName("uv") val uv: Float? = null
)