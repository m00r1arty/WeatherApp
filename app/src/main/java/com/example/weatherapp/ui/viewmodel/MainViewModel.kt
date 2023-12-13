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

    private val _currentWeatherCardFahrenheit = MutableLiveData<CurrentWeatherCardModel>()
    val currentWeatherFahrenheit: LiveData<CurrentWeatherCardModel>
        get() = _currentWeatherCardFahrenheit

    private val _daysListFahrenheit = MutableLiveData<List<DaysWeatherItemModel>>()
    val daysListFahrenheit: LiveData<List<DaysWeatherItemModel>>
        get() = _daysListFahrenheit

    fun getCurrentWeatherCard(cityName: String) {
        viewModelScope.launch {
            try {
                val weatherData = repository.getCurrentWeatherCard(cityName)
                weatherData?.let {
                    _currentWeatherCard.value = it
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("LOGGGG: ", it) }
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

    fun getCurrentWeatherCardFahrenheit(cityName: String) {
        viewModelScope.launch {
            try {
                val weatherData = repository.getCurrentWeatherCardFahrenheit(cityName)
                weatherData?.let {
                    _currentWeatherCard.value = it
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("LOGGGG: ", it) }
            }
        }
    }

    fun updateDaysListFahrenheit(cityName: String) {
        viewModelScope.launch {
            try {
                val daysListData = repository.getDaysItemWeatherFahrenheit(cityName)
                daysListData?.let {
                    _daysList.value = it
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("LOGGGG: ", it) }
            }
        }
    }

}
