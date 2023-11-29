package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.databinding.FragmentDaysBinding
import com.example.weatherapp.domain.daysextensions.InitDataFromDays.initRecyclerView
import com.example.weatherapp.domain.daysextensions.InitDataFromDays.observeWeatherData
import com.example.weatherapp.ui.adapter.WeatherAdapter.Listener
import com.example.weatherapp.ui.adapter.WeatherAdapter
import com.example.weatherapp.ui.viewmodel.MainViewModel

class DaysFragment : Fragment(), Listener {
    // Привязка для доступа к Views в макете фрагмента
    internal lateinit var binding: FragmentDaysBinding

    // Адаптер для отображения данных о погоде
    internal lateinit var adapter: WeatherAdapter

    // ViewModel для взаимодействия с данными
    internal val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Инициализация привязки макета фрагмента
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация RecyclerView для отображения данных о днях
        initRecyclerView()

        // Наблюдение за данными о погоде в ViewModel
        observeWeatherData()
    }

    // Обработчик клика по карточке погоды
    override fun onCLickWeatherCard(item: WeatherModel) {
        // Установка текущей погоды в ViewModel при клике на карточку
        viewModel.liveDataCurrent.value = item
    }

    // Создание нового экземпляра DaysFragment
    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}
