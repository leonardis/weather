package com.leonardis.weather.models

import com.google.gson.annotations.SerializedName

data class Location(
    val name: String = "NO_NAME",
    @SerializedName("coord")
    val coordinates: Coordinates
)

