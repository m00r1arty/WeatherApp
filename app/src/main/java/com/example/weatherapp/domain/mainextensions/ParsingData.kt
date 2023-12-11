//package com.example.weatherapp.domain.mainextensions
//
//import android.util.Log
//import com.example.weatherapp.domain.model.WeatherModel
//import com.example.weatherapp.ui.fragments.MainFragment
//import org.json.JSONObject
//
//object ParsingData {
//    /**
//     * Запрос данных о погоде с использованием Volley.
//     *
//     * @param city Название города для запроса погоды.
//     */
//    /**
//     * Обрабатывает ошибку запроса погоды.
//     *
//     * @param error Объект ошибки Volley.
//     */
//    private fun handleWeatherRequestError(error: VolleyError) {
//        Log.e("MyLog", "Error $error")
//    }
//
//    /**
//     * Парсит данные о погоде из JSON-строки.
//     *
//     * @param result JSON-строка с данными о погоде.
//     */
//    private fun MainFragment.parseWeatherData(result: String) {
//        val mainObject = JSONObject(result)
//        val list = parseDays(mainObject)
//        parseCurrentData(mainObject, list[0])
//    }
//
//    /**
//     * Парсит данные о погоде на несколько дней из JSON-объекта.
//     *
//     * @param mainObject JSON-объект с данными о погоде.
//     * @return Список моделей погоды на несколько дней.
//     */
//    private fun MainFragment.parseDays(mainObject: JSONObject): List<WeatherModel> {
//        val list = mutableListOf<WeatherModel>()
//        val name = mainObject.getJSONObject("location").getString("name")
//        val daysArray = mainObject.getJSONObject("forecast")
//            .getJSONArray("forecastday")
//        for (i in 0 until daysArray.length()) {
//            val day = daysArray[i] as JSONObject
//            val item = WeatherModel(
//                name,
//                day.getString("date"),
//                day.getJSONObject("day").getJSONObject("condition")
//                    .getString("text"),
//                "",
//                day.getJSONObject("day").getString("maxtemp_c")
//                    .toFloat().toInt().toString(),
//                day.getJSONObject("day").getString("mintemp_c")
//                    .toFloat().toInt().toString(),
//                day.getJSONObject("day").getJSONObject("condition")
//                    .getString("icon"),
//                day.getJSONArray("hour").toString()
//            )
//            list.add(item)
//        }
//        viewModel.liveDataList.value = list
//        return list
//    }
//
//    /**
//     * Парсит текущие данные о погоде из JSON-объекта.
//     *
//     * @param mainObject JSON-объект с данными о погоде.
//     * @param weatherItem Модель погоды для получения некоторых значений.
//     */
//    private fun MainFragment.parseCurrentData(mainObject: JSONObject, weatherItem: WeatherModel) {
//        val currentData = mainObject.getJSONObject("current")
//        viewModel.liveDataCurrent.value = WeatherModel(
//            mainObject.getJSONObject("location").getString("name"),
//            currentData.getString("last_updated"),
//            currentData.getJSONObject("condition").getString("text"),
//            currentData.getString("temp_c").toFloat().toInt().toString(),
//            weatherItem.maxTemp,
//            weatherItem.minTemp,
//            currentData.getJSONObject("condition").getString("icon"),
//            weatherItem.hours
//        )
//    }
//}
