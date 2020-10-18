package com.leonardis.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonardis.weather.R
import com.leonardis.weather.adapters.ForecastAdapter
import com.leonardis.weather.adapters.LocationsAdapter
import com.leonardis.weather.models.Forecast
import com.leonardis.weather.ui.MainActivity
import com.leonardis.weather.utils.DAY_MONTH
import com.leonardis.weather.utils.METRIC_UNITS
import com.leonardis.weather.utils.SharedPreferencesUtils
import com.leonardis.weather.utils.UNITS
import com.leonardis.weather.utils.convertDate
import com.leonardis.weather.viewmodels.ForecastViewModel
import kotlinx.android.synthetic.main.fragment_location_detail.date
import kotlinx.android.synthetic.main.fragment_location_detail.feels
import kotlinx.android.synthetic.main.fragment_location_detail.locationName
import kotlinx.android.synthetic.main.fragment_location_detail.locationTemperature
import kotlinx.android.synthetic.main.fragment_location_detail.rv_forecast
import kotlinx.android.synthetic.main.fragment_location_detail.toolbar
import kotlin.math.roundToInt

class LocationDetailFragment : BaseFragment() {

    private val viewModel: ForecastViewModel by viewModels()
    private val locationArgs: LocationDetailFragmentArgs by navArgs()

    private val forecastAdapter: ForecastAdapter = ForecastAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            viewModel.weather = locationArgs.location
            viewModel.getForecast(SharedPreferencesUtils.getStringData(requireContext(), UNITS, METRIC_UNITS))
        } catch (exception: Exception) {
            exception.printStackTrace()
            showError()
        }

        setupToolbar()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).showBottomNavBar(false)
        viewModel.getForecast(SharedPreferencesUtils.getStringData(requireContext(), UNITS, METRIC_UNITS))
        updateUI()
        subscribe()
    }

    private fun updateUI() {
        locationTemperature.text = getString(R.string.temperature, viewModel.weather?.main?.temp?.roundToInt())
        date.text = convertDate(viewModel.weather?.dateTime ?: 0L, expectedFormat = DAY_MONTH)
        locationName.text = viewModel.weather?.name
        feels.text = getString(R.string.feels_like, viewModel.weather?.main?.feelsLike?.roundToInt())
    }

    private fun subscribe() {
        viewModel.forecastData.observe(viewLifecycleOwner, Observer { response ->
            val items = response.list.groupBy {
                it.dtTxt.removeRange(it.dtTxt.length - 9, it.dtTxt.length)
            }.map {
                it.value.first()
            }
            forecastAdapter.setData(items)
        })
    }

    private fun showError() {

    }

    private fun setupToolbar() {
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setupRecyclerView() {
        rv_forecast.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = forecastAdapter
        }
    }


}

