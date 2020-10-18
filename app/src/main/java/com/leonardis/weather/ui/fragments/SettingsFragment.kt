package com.leonardis.weather.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.leonardis.weather.R
import com.leonardis.weather.utils.IMPERIAL_UNITS
import com.leonardis.weather.utils.METRIC_UNITS
import com.leonardis.weather.utils.SharedPreferencesUtils
import com.leonardis.weather.utils.UNITS
import kotlinx.android.synthetic.main.fragment_settings.about
import kotlinx.android.synthetic.main.fragment_settings.change_unit
import kotlinx.android.synthetic.main.fragment_settings.metricValue


class SettingsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMetricUnits()

        about.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_aboutFragment)
        }

        change_unit.setOnClickListener { showUnitsDialog() }
        metricValue.setOnClickListener { showUnitsDialog() }
    }

    private fun setMetricUnits() {
        metricValue.text = SharedPreferencesUtils.getStringData(requireContext(), UNITS, METRIC_UNITS)
    }

    private fun showUnitsDialog() {
        val colors = arrayOf(METRIC_UNITS, IMPERIAL_UNITS)

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.change_units)
        builder.setItems(colors) { _, value ->
            SharedPreferencesUtils.saveData(requireContext(), UNITS, colors[value])
            setMetricUnits()
        }
        builder.show()
    }

}

