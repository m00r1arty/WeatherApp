package com.example.weatherapp.data.model.response.responsebody

import com.google.gson.annotations.SerializedName

data class DayResponse(
    @SerializedName("maxtemp_c") val maxtemp_c: Float? = null,
    @SerializedName("mintemp_c") val mintemp_c: Float? = null,
    @SerializedName("condition") val condition: Condition? = null,
)