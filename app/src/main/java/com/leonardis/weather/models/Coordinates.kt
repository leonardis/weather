package com.leonardis.weather.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinates(
    @SerializedName("lat")
    val latitude: Double? = 0.0,
    @SerializedName("lon")
    val longitude: Double? = 0.0
): Parcelable