package com.example.weatherapp.domain.hoursextensions

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.ui.adapter.WeatherAdapter
import com.example.weatherapp.ui.fragments.HoursFragment
import org.json.JSONArray

object InitDataFromHours {
    /**
     * Инициализирует RecyclerView для отображения данных о часах.
     */
    fun HoursFragment.initRecyclerView() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(null)
        recyclerView.adapter = adapter
    }

    /**
     * Наблюдает за данными о текущей погоде и обновляет RecyclerView.
     */
    fun HoursFragment.observeWeatherData() {
        viewModel.liveDataCurrent.observe(viewLifecycleOwner) {
            adapter.submitList(getHoursList(it))
        }
    }

    /**
     * Получает список моделей погоды для каждого часа из JSON-строки.
     *
     * @param weatherItem Модель погоды с информацией о часах.
     * @return Список моделей погоды для каждого часа.
     */
    private fun getHoursList(weatherItem: WeatherModel): List<WeatherModel> {
        val hoursArray = JSONArray(weatherItem.hours)
        return (0 until hoursArray.length()).map { i ->
            val hourObject = hoursArray.getJSONObject(i)
            WeatherModel(
                weatherItem.city,
                hourObject.getString("time"),
                hourObject.getJSONObject("condition").getString("text"),
                hourObject.getString("temp_c").toFloat().toInt().toString(),
                "",
                "",
                hourObject.getJSONObject("condition").getString("icon"),
                ""
            )
        }
    }
}

