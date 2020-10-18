package com.leonardis.weather.ui.fragments

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.leonardis.weather.R
import com.leonardis.weather.adapters.LocationsAdapter
import com.leonardis.weather.models.Coordinates
import com.leonardis.weather.models.WeatherResponse
import com.leonardis.weather.ui.MainActivity
import com.leonardis.weather.utils.CurrentLocation
import com.leonardis.weather.utils.METRIC_UNITS
import com.leonardis.weather.utils.SharedPreferencesUtils
import com.leonardis.weather.utils.UNITS
import com.leonardis.weather.utils.permissionIsGranted
import com.leonardis.weather.viewmodels.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_locations.rv_favorites
import kotlinx.android.synthetic.main.fragment_locations.rv_weather


class LocationsFragment : BaseFragment() {

    private val viewModel: WeatherViewModel by viewModels()

    private lateinit var fusedLocationProvider: FusedLocationProviderClient

    private val weatherAdapter: LocationsAdapter = LocationsAdapter{ openLocation(it) }
    private val favoritesAdapter: LocationsAdapter = LocationsAdapter{ openLocation(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(requireContext())

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        requestCurrentLocation()
        showBottomNavBar(true)
        viewModel.getFavoritesData(getFavorites(), SharedPreferencesUtils.getStringData(requireContext(), UNITS, METRIC_UNITS))
        subscribe()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_CODE) {
            if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                requestCurrentLocation()
            }
        }
    }

    private fun requestCurrentLocation() {
        if (!permissionIsGranted(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) &&
            !permissionIsGranted(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_CODE
            )
            return
        }

        val task = fusedLocationProvider.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                CurrentLocation.location = location
                viewModel.getSuggestedData(getSuggestedCoordinates(), SharedPreferencesUtils.getStringData(requireContext(), UNITS, METRIC_UNITS))
            }
        }
    }

    private fun getSuggestedCoordinates(): List<Coordinates> {
        val latitudes = resources.getStringArray(R.array.suggestedLatitude)
        val longitudes = resources.getStringArray(R.array.suggestedLongitude)

        return latitudes.zip(longitudes).map {
            Coordinates(it.first.toDouble(), it.second.toDouble())
        }.toMutableList().apply {
            CurrentLocation.location?.let {
                add(0, CurrentLocation.locationToCoordinates())
            }
        }.toList()
    }

    private fun getFavorites(): List<Coordinates?> {
        return SharedPreferencesUtils.getFavorites(requireContext()).map {
            it.coordinates?.let {
                it
            }
        }
    }

    private fun subscribe() {
        viewModel.suggestedWeatherData.observe(viewLifecycleOwner, Observer {
            weatherAdapter.setData(it)
        })

        viewModel.favoriteWeatherData.observe(viewLifecycleOwner, Observer {
            favoritesAdapter.setData(it)
        })
    }

    private fun setupRecyclerView() {
        rv_weather.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = weatherAdapter
        }

        rv_favorites.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = favoritesAdapter
        }
    }

    private fun openLocation(location: WeatherResponse) {
        showBottomNavBar(false)
        val action = LocationsFragmentDirections.actionLocationsFragmentToLocationDetailFragment(location)
        findNavController().navigate(action)
    }


    companion object {
        private const val LOCATION_CODE = 101
    }
}

