package com.example.weatherapp.data.model.response.responsebody

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("region") private val region: String? = null,
    @SerializedName("country") private val country: String? = null,
    @SerializedName("lat")  private val lat: Float? = null,
    @SerializedName("lon") private val lon: Float? = null,
    @SerializedName("tz_id") private val tzId: String? = null,
    @SerializedName("localtime_epoch") private val localtimeEpoch: Long? = null,
    @SerializedName("localtime") private val localtime: String? = null,
)
