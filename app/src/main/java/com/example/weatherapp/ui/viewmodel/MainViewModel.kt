package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
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
    var isFahrenheit = false

    private val _currentWeatherCard = MutableLiveData<CurrentWeatherCardModel>()
    val currentWeather: LiveData<CurrentWeatherCardModel> =_currentWeatherCard.map { weather ->
        if (isFahrenheit) {
            weather.copy(
                currentTemp = convertCelsiusToFahrenheit(weather.currentTemp),
                maxTemp = convertCelsiusToFahrenheit(weather.maxTemp),
                minTemp = convertCelsiusToFahrenheit(weather.minTemp)
            )
        } else {
            weather
        }
    }

    private val _daysList = MutableLiveData<List<DaysWeatherItemModel>>()
    val daysList = _daysList.map { list  ->
        list.map { item ->
            if (isFahrenheit) {
                item.copy(
                    maxTemp = convertCelsiusToFahrenheit(item.maxTemp),
                    minTemp = convertCelsiusToFahrenheit(item.minTemp)
                )
            } else {
                item
            }
        }
    }

    fun updateWeather(location: String) = viewModelScope.launch {
        repository.getCurrentWeatherCard(location)?.let { item ->
            _currentWeatherCard.value = item
        }
        _daysList.value = repository.getDaysItemWeather(location)
    }
    fun invalidateData() {
        _daysList.value = _daysList.value
        _currentWeatherCard.value = _currentWeatherCard.value
    }

    private fun convertCelsiusToFahrenheit(celsius: Float?): Float? {
        if (celsius == null) return null
        return floor((celsius * 9 / 5) + 32)
    }
}
