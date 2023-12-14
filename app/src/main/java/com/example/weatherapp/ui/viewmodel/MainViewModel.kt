package com.example.weatherapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repositories.WeatherRepository
import com.example.weatherapp.domain.model.CurrentWeatherCardModel
import com.example.weatherapp.domain.model.DaysWeatherItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.floor

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {
    private val _currentWeatherCard = MutableLiveData<CurrentWeatherCardModel>()
    val currentWeather: LiveData<CurrentWeatherCardModel>
        get() = _currentWeatherCard

    private val _daysList = MutableLiveData<List<DaysWeatherItemModel>>()
    val daysList: LiveData<List<DaysWeatherItemModel>>
        get() = _daysList

    fun getCurrentWeatherCard(cityName: String, isFahrenheit: Boolean = false) {
        viewModelScope.launch {
            try {
                val weatherCurrentCard = repository.getCurrentWeatherCard(cityName)
                weatherCurrentCard?.let {
                    if (isFahrenheit) {
                        val currentTemp = convertCelsiusToFahrenheit(it.currentTemp)
                        val maxTemp = convertCelsiusToFahrenheit(it.maxTemp)
                        val minTemp = convertCelsiusToFahrenheit(it.minTemp)

                        _currentWeatherCard.value = it.copy(
                            currentTemp = currentTemp,
                            maxTemp = maxTemp,
                            minTemp = minTemp
                        )
                    } else {
                        _currentWeatherCard.value = it
                    }
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("Error", it) }
            }
        }
    }

    fun updateDaysList(cityName: String, isFahrenheit: Boolean = false) {
        viewModelScope.launch {
            try {
                val daysListData = repository.getDaysItemWeather(cityName)
                daysListData?.let {
                    if (isFahrenheit) {
                        val convertedList = it.map { day ->
                            day.copy(
                                maxTemp = convertCelsiusToFahrenheit(day.maxTemp),
                                minTemp = convertCelsiusToFahrenheit(day.minTemp)
                            )
                        }
                        _daysList.value = convertedList
                    } else {
                        _daysList.value = it
                    }
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("LOGGGG: ", it) }
            }
        }
    }


    private fun convertCelsiusToFahrenheit(celsius: Float?): Float? {
        if (celsius == null) return null
        return floor((celsius * 9 / 5) + 32)
//        return "%.1f".format((celsius * 9 / 5) + 32).toFloat()
    }
}
