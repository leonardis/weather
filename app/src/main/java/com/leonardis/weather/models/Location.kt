package com.leonardis.weather.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    @SerializedName("coord")
    val coordinates: Coordinates? = null,
    @SerializedName("country")
    val country: String? = "",
    @SerializedName("id")
    val id: Int? = -1,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("population")
    val population: Int? = -1,
    @SerializedName("sunrise")
    val sunrise: Int? = -1,
    @SerializedName("sunset")
    val sunset: Int? =-1,
    @SerializedName("timezone")
    val timezone: Int? = -1,
    val isFavorite: Boolean = false
) : Parcelable

