package com.example.weatherapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repositories.WeatherRepository
import com.example.weatherapp.domain.model.CurrentWeatherCardModel
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

    fun getCurrentWeatherCard() {
        viewModelScope.launch {
            try {
                val apiKey = "516e8d43fcac405c8a392705231411"
                val cityName = "Kirov-Chepetsk"
                val weatherData = repository.getCurrentWeatherCard(apiKey, cityName)
                weatherData?.let {
                    _currentWeatherCard.value = it
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("LOGGGG: ", it) }
            }
        }
    }
}
