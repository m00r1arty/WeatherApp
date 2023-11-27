package com.example.weatherapp.domain.daysextensions

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.ui.adapter.WeatherAdapter
import com.example.weatherapp.ui.fragments.DaysFragment

object InitDataFromDays {

    fun DaysFragment.initRecyclerView() {
        adapter = WeatherAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = adapter
    }

    fun DaysFragment.observeWeatherData() {
        viewModel.liveDataList.observe(viewLifecycleOwner) {
            adapter.submitList(it.subList(1, it.size))
        }
    }

}
