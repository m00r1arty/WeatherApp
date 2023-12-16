package com.example.weatherapp

import com.example.weatherapp.data.model.response.WeatherResponse
import com.example.weatherapp.data.network.WeatherApi
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Response

class WeatherApiTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var mockCall: Call<WeatherResponse>

    @Mock
    private lateinit var weatherApi: WeatherApi


    @Test
    fun getWeatherForecastSuccessTest() {
        val apiKey = "6183a5484fc64341bce122716231312"
        val cityName = "London"
        val days = 1
        val aqi = "no"
        val alerts = "no"

        whenever(weatherApi.getWeatherForecast(apiKey, cityName, days, aqi, alerts)).thenReturn(mockCall)
        whenever(mockCall.execute()).thenReturn(Response.success(WeatherResponse()))

        val response = weatherApi.getWeatherForecast(apiKey, cityName, days, aqi, alerts).execute()

        assert(response.isSuccessful)
    }

    @Test
    fun getWeatherForecastErrorTest() {
        val apiKey = "6183a5484fc64341bce122716231312"
        val cityName = "London"
        val days = 1
        val aqi = "no"
        val alerts = "no"

        whenever(weatherApi.getWeatherForecast(apiKey, cityName, days, aqi, alerts)).thenReturn(mockCall)
        whenever(mockCall.execute()).thenReturn(Response.error(404, "".toResponseBody(null)))

        val response = weatherApi.getWeatherForecast(apiKey, cityName, days, aqi, alerts).execute()

        assert(!response.isSuccessful)
    }

}