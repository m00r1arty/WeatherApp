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

        whenever(weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS)).thenReturn(mockCall)
        whenever(mockCall.execute()).thenReturn(Response.success(WeatherResponse()))

        val response = weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS).execute()

        assert(response.isSuccessful)
    }

    @Test
    fun getWeatherForecastErrorTest() {
        whenever(weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS)).thenReturn(mockCall)
        whenever(mockCall.execute()).thenReturn(Response.error(404, "".toResponseBody(null)))

        val response = weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS).execute()

        assert(!response.isSuccessful)
    }

    companion object {
        private const val API_KEY = "6183a5484fc64341bce122716231312"
        private const val CITY = "London"
        private const val DAYS = 3
        private const val AQI = "no"
        private const val ALERTS = "no"
    }

}