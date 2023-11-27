package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.databinding.FragmentHoursBinding
import com.example.weatherapp.domain.hoursextensions.InitDataFromHours.initRecyclerView
import com.example.weatherapp.domain.hoursextensions.InitDataFromHours.observeWeatherData
import com.example.weatherapp.ui.adapter.WeatherAdapter
import com.example.weatherapp.ui.viewmodel.MainViewModel

class HoursFragment : Fragment() {
    internal lateinit var binding: FragmentHoursBinding
    internal lateinit var adapter: WeatherAdapter
    internal val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeWeatherData()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}