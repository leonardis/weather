package com.leonardis.weather.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


fun permissionIsGranted(context: Context, permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}

fun requestLocationPermission() {

}