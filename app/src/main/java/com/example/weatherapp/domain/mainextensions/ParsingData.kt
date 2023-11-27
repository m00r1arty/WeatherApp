package com.example.weatherapp.domain.mainextensions

import android.util.Log
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.domain.Constants
import com.example.weatherapp.ui.fragments.MainFragment
import org.json.JSONObject

object ParsingData {
    fun MainFragment.requestWeatherData(city: String) {
        Volley.newRequestQueue(requireContext()).add(
            StringRequest(
                Request.Method.GET,
                buildWeatherApiUrl(city),
                { result -> parseWeatherData(result) },
                { error -> handleWeatherRequestError(error) }
            )
        )
    }

    private fun buildWeatherApiUrl(city: String): String =
        "${Constants.BASE_URL}${Constants.API_KEY}&q=$city&days=6&aqi=no&alerts=no"

    private fun handleWeatherRequestError(error: VolleyError) {
        Log.e("MyLog", "Error $error")
    }

    private fun MainFragment.parseWeatherData(result: String) {
        val mainObject = JSONObject(result)
        val list = parseDays(mainObject)
        parseCurrentData(mainObject, list[0])
    }

    private fun MainFragment.parseDays(mainObject: JSONObject): List<WeatherModel> {
        val list = mutableListOf<WeatherModel>()
        val name = mainObject.getJSONObject("location").getString("name")
        val daysArray = mainObject.getJSONObject("forecast")
            .getJSONArray("forecastday")
        for (i in 0 until daysArray.length()) {
            val day = daysArray[i] as JSONObject
            val item = WeatherModel(
                name,
                day.getString("date"),
                day.getJSONObject("day").getJSONObject("condition")
                    .getString("text"),
                "",
                day.getJSONObject("day").getString("maxtemp_c")
                    .toFloat().toInt().toString(),
                day.getJSONObject("day").getString("mintemp_c")
                    .toFloat().toInt().toString(),
                day.getJSONObject("day").getJSONObject("condition")
                    .getString("icon"),
                day.getJSONArray("hour").toString()
            )
            list.add(item)
        }
        viewModel.liveDataList.value = list
        return list
    }

    private fun MainFragment.parseCurrentData(mainObject: JSONObject, weatherItem: WeatherModel) {
        val currentData = mainObject.getJSONObject("current")
        viewModel.liveDataCurrent.value = WeatherModel(
            mainObject.getJSONObject("location").getString("name"),
            currentData.getString("last_updated"),
            currentData.getJSONObject("condition").getString("text"),
            currentData.getString("temp_c").toFloat().toInt().toString(),
            weatherItem.maxTemp,
            weatherItem.minTemp,
            currentData.getJSONObject("condition").getString("icon"),
            weatherItem.hours
        )
    }
}