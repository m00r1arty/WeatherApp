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

        whenever(weatherApi.getWeatherForecast("6183a5484fc64341bce122716231312", "London", 1, "no", "no"))
            .thenReturn(mockCall)

        whenever(mockCall.execute()).thenReturn(Response.success(WeatherResponse()))

        val result = weatherRepository.getCurrentWeatherCard("London")

        assertNotNull(result)
    }

    @Test
    fun getCurrentWeatherCardErrorTest() = runTest {

        whenever(weatherApi.getWeatherForecast("6183a5484fc64341bce122716231312", "CityName", 1, "no", "no"))
            .thenThrow(RuntimeException("API call failed"))

        val result = weatherRepository.getCurrentWeatherCard("London")

        assertNull(result)
    }

    @Test
    fun getDaysItemWeatherSuccessTest() = runTest {

        whenever(weatherApi.getWeatherForecast("6183a5484fc64341bce122716231312", "London", 1, "no", "no"))
            .thenReturn(mockCall)

        whenever(mockCall.execute()).thenReturn(Response.success(WeatherResponse()))

        val result = weatherRepository.getDaysItemWeather("London")

        assertNotNull(result)
    }

    @Test
    fun getDaysItemWeatherErrorTest() = runTest {

        whenever(weatherApi.getWeatherForecast("6183a5484fc64341bce122716231312", "CityName", 1, "no", "no"))
            .thenThrow(RuntimeException("API call failed"))

        val result = weatherRepository.getDaysItemWeather("London")

        assert(result.isEmpty())
    }
}