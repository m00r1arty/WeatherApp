package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.domain.model.WeatherModel

class MainViewModel : ViewModel() {
    // LiveData для текущей погоды
    val liveDataCurrent = MutableLiveData<WeatherModel>()

    // LiveData для списка моделей погоды
    val liveDataList = MutableLiveData<List<WeatherModel>>()
}
