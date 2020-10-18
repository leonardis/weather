package com.leonardis.weather.ui.fragments

import androidx.fragment.app.Fragment
import com.leonardis.weather.ui.MainActivity

open class BaseFragment: Fragment() {
    fun showBottomNavBar(show: Boolean) {
        (requireActivity() as MainActivity).showBottomNavBar(show)
    }
}