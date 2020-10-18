package com.leonardis.weather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.leonardis.weather.R
import com.leonardis.weather.models.WeatherResponse
import com.leonardis.weather.utils.SharedPreferencesUtils
import com.leonardis.weather.utils.display
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.navigation_hostFragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    fun showBottomNavBar(show: Boolean) {
        bottomNavigationView.display(show)
    }

    fun showSnackBar(text: String, item: WeatherResponse) {
        Snackbar
            .make(bottomNavigationView, text, Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.add)) {
                SharedPreferencesUtils.addFavorite(this, item)
            }.show()
    }
}