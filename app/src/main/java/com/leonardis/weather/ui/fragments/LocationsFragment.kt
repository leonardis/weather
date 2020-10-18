package com.leonardis.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonardis.weather.R
import com.leonardis.weather.adapters.LocationsAdapter
import com.leonardis.weather.models.Location
import kotlinx.android.synthetic.main.fragment_locations.rv_favorites
import kotlinx.android.synthetic.main.fragment_locations.rv_weather

class LocationsFragment : Fragment() {

    val weatherAdapter: LocationsAdapter = LocationsAdapter()
    val favoritesAdapter: LocationsAdapter = LocationsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        setData()
        setDataFav()
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

    private fun setData() {
        val data = mutableListOf<Location>().apply {
            for (i in 1..10) {
                add(Location(i.toString()))
            }
        }
        weatherAdapter.setData(data)
    }

    private fun setDataFav() {
        val data = mutableListOf<Location>().apply {
            for (i in 1..10) {
                add(Location(i.toString(), isFavorite = true))
            }
        }
        favoritesAdapter.setData(data)
    }
}

