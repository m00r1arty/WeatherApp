package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

}
