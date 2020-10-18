package com.leonardis.weather.utils

import java.text.SimpleDateFormat
import java.util.Date

internal const val DAY_OF_THE_WEEK = "EEE"
internal const val DAY_MONTH = "EEE, dd MMM"
internal const val FULL_DATE = "yyyy-MM-dd HH:mm:ss"

fun convertDate(timeInMillis: Long, expectedFormat: String): String {
    return try {
        var date: String? = null
        val formatter = SimpleDateFormat(expectedFormat) // modify format
        date = formatter.format(Date(timeInMillis * 1000L))
        date
    } catch (exception: Exception) {
        ""
    }
}