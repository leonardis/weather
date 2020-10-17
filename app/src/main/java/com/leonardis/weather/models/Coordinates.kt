package com.leonardis.weather.models

import com.google.gson.annotations.SerializedName

data class Coordinates(
    @SerializedName("lat")
    val latitude: Double? = 0.0,
    @SerializedName("lon")
    val longitude: Double? = 0.0
)