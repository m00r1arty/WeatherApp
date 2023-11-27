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
    internal lateinit var binding: FragmentDaysBinding
    internal lateinit var adapter: WeatherAdapter
    internal val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeWeatherData()
    }

    override fun onCLickWeatherCard(item: WeatherModel) {
        viewModel.liveDataCurrent.value = item
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}