package com.example.weatherapp.data.repositories

import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.domain.model.WeatherModel
import com.example.weatherapp.domain.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {
    private val BASE_URL = "https://api.weatherapi.com/v1/"

    fun getWeather(
        apiKey: String,
        cityName: String,
        onResponse: (WeatherModel) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val weatherApi = retrofit.create(WeatherApi::class.java)
        val call = weatherApi.getWeatherForecast(apiKey, cityName, 1, "no", "no")

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherApiResponse = response.body()

                    val weatherModel = WeatherModel(
                        nameCity = weatherApiResponse?.location?.name ?: "",
                        dateTime = weatherApiResponse?.current?.lastUpdated ?: "",
                        conditionText = weatherApiResponse?.current?.condition?.text ?: "",
                        currentTemp = weatherApiResponse?.current?.tempC ?: 0.0f,
                        maxTemp = weatherApiResponse?.forecast?.forecastDay?.get(0)?.day?.maxtemp_c ?: 0.0f,
                        minTemp = weatherApiResponse?.forecast?.forecastDay?.get(0)?.day?.mintemp_c ?: 0.0f,
                        imageUrl = weatherApiResponse?.current?.condition?.icon ?: "",
                        hours = weatherApiResponse?.forecast?.forecastDay?.get(0)?.hour?.joinToString(", ") { it.time.toString() } ?: ""
                    )

                    onResponse.invoke(weatherModel)
                } else {
                    onFailure.invoke("Ошибка при получении данных о погоде. Код ошибки: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                onFailure.invoke("Ошибка при выполнении запроса: ${t.localizedMessage}")
                t.printStackTrace()
            }
        })
    }
}
