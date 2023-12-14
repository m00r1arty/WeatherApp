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

    fun getCurrentWeatherCard(cityName: String) {
        viewModelScope.launch {
            try {
                val weatherCurrentCard = repository.getCurrentWeatherCard(cityName)
                weatherCurrentCard?.let {
                    _currentWeatherCard.value = it
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("LOGGGG: ", it) }
            }
        }
    }

    fun getCurrentWeatherCardFahrenheit(cityName: String) {
        viewModelScope.launch {
            try {
                val weatherCurrentCard = repository.getCurrentWeatherCard(cityName)
                val currentTemp = _currentWeatherCard.value?.currentTemp
                val maxTemp = _currentWeatherCard.value?.maxTemp
                val minTemp = _currentWeatherCard.value?.minTemp
                val convertCToFCurrentTemp = convertCelsiusToFahrenheit(currentTemp)
                val convertCToFMaxTemp = convertCelsiusToFahrenheit(maxTemp)
                val convertCToFMinTemp = convertCelsiusToFahrenheit(minTemp)
                weatherCurrentCard?.let {
                    _currentWeatherCard.value = it.copy(
                        currentTemp = convertCToFCurrentTemp,
                        maxTemp = convertCToFMaxTemp,
                        minTemp = convertCToFMinTemp
                    )
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("Error", it) }
            }
        }
    }

    fun updateDaysList(cityName: String) {
        viewModelScope.launch {
            try {
                val daysListData = repository.getDaysItemWeather(cityName)
                daysListData?.let {
                    _daysList.value = it
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("LOGGGG: ", it) }
            }
        }
    }

    fun updateDaysListFahrenheit(cityName: String) {
        viewModelScope.launch {
            try {
                val daysList = repository.getDaysItemWeather(cityName)
                val maxTemp = _daysList.value?.get(0)?.maxTemp
                val minTemp = _daysList.value?.get(0)?.minTemp
                val convertCToFMaxTemp = convertCelsiusToFahrenheit(maxTemp)
                val convertCToFMinTemp = convertCelsiusToFahrenheit(minTemp)
                daysList?.let {
                    _daysList.value = it.map { day ->
                        day.copy(
                            maxTemp = convertCToFMaxTemp,
                            minTemp = convertCToFMinTemp
                        )
                    }
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("Error", it) }
            }
        }
    }



    private fun convertCelsiusToFahrenheit(celsius: Float?): Float? {
        if (celsius == null) return null
        return (celsius * 9 / 5) + 32
//        return "%.1f".format((celsius * 9 / 5) + 32).toFloat()
    }
}
