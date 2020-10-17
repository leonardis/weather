package com.leonardis.weather.models

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country")
    val country: String,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int
)