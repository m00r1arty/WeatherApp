package com.example.weatherapp.domain.daysextensions

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.ui.adapter.WeatherAdapter
import com.example.weatherapp.ui.fragments.DaysFragment

object InitDataFromDays {
    /**
     * Инициализирует RecyclerView для отображения данных о днях.
     */
    fun DaysFragment.initRecyclerView() {
        adapter = WeatherAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = adapter
    }

    /**
     * Наблюдает за данными о списке погоды на несколько дней и обновляет RecyclerView.
     */
    fun DaysFragment.observeWeatherData() {
        viewModel.liveDataList.observe(viewLifecycleOwner) {
            // Исключает первый элемент, так как он уже отображается в текущей погоде
            adapter.submitList(it.subList(1, it.size))
        }
    }
}

