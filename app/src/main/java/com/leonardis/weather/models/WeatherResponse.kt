package com.leonardis.weather.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherResponse(
    @SerializedName("base")
    val base: String? = "",
    @SerializedName("clouds")
    val clouds: Clouds? = null,
    @SerializedName("cod")
    val weatherCode: Int? = -1,
    @SerializedName("coord")
    val coordinates: Coordinates? = null,
    @SerializedName("dt")
    val dateTime: Long? = -1,
    @SerializedName("dt_txt")
    val date: String? = "",
    @SerializedName("id")
    val id: Int? = -1,
    @SerializedName("main")
    val main: Main? = null,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("sys")
    val country: Country? = null,
    @SerializedName("timezone")
    val timezone: Int? = -1,
    @SerializedName("visibility")
    val visibility: Int? = -1,
    @SerializedName("weather")
    val weather: List<Weather>? = emptyList(),
    @SerializedName("wind")
    val wind: Wind? = null,
    var isFavorite: Boolean = false
): Parcelable

