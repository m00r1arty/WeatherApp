package com.example.weatherapp

import com.example.weatherapp.data.model.response.WeatherResponse
import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.data.repositories.WeatherRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Response

class WeatherRepositoryTest {

    @Mock
    private lateinit var weatherApi: WeatherApi

    @Mock
    private lateinit var mockCall: Call<WeatherResponse>

    @InjectMocks
    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        weatherRepository = WeatherRepository(weatherApi)
    }

    @Test
    fun getCurrentWeatherCardSuccessTest() = runTest {

        whenever(weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS))
            .thenReturn(mockCall)

        whenever(mockCall.execute()).thenReturn(Response.success(WeatherResponse()))

        val result = weatherRepository.getCurrentWeatherCard(CITY)

        assertNotNull(result)
    }

    @Test
    fun getCurrentWeatherCardErrorTest() = runTest {

        whenever(weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS))
            .thenThrow(RuntimeException(API_CALL_FAILED))

        val result = weatherRepository.getCurrentWeatherCard(CITY)

        assertNull(result)
    }

    @Test
    fun getDaysItemWeatherSuccessTest() = runTest {

        whenever(weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS))
            .thenReturn(mockCall)

        whenever(mockCall.execute()).thenReturn(Response.success(WeatherResponse()))

        val result = weatherRepository.getDaysItemWeather(CITY)

        assertNotNull(result)
    }

    @Test
    fun getDaysItemWeatherErrorTest() = runTest {

        whenever(weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS))
            .thenThrow(RuntimeException(API_CALL_FAILED))

        val result = weatherRepository.getDaysItemWeather(CITY)

        assert(result.isEmpty())
    }

    companion object {
        private const val API_CALL_FAILED = "API call failed"
        private const val API_KEY = "6183a5484fc64341bce122716231312"
        private const val CITY = "London"
        private const val DAYS = 3
        private const val AQI = "no"
        private const val ALERTS = "no"
    }
}