package com.leonardis.weather.utils

import android.location.Location
import com.leonardis.weather.models.Coordinates

object CurrentLocation {
    var location: Location? = null

    fun locationToCoordinates(): Coordinates {
        return Coordinates(location?.latitude, location?.longitude)
    }
}