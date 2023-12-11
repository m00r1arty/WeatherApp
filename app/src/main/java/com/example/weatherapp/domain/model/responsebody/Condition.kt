package com.example.weatherapp.domain.model.responsebody

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("text") val text: String? = null,
    @SerializedName("icon") val icon: String? = null,
    @SerializedName("code") private val code: Long? = null,
)
