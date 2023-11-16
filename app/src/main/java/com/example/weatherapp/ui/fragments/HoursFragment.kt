package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.databinding.FragmentHoursBinding
import com.example.weatherapp.ui.adapter.WeatherAdapter

class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter

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
    }

    private fun initRecyclerView() = with(binding){
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        recyclerView.adapter = adapter
        val list = listOf(
            WeatherModel("", "13:00",
                "Sunny", "-1*C",
                "", "", "", ""),

            WeatherModel("", "14:00",
                "Cloudy", "-1*C",
                "", "", "", ""),

            WeatherModel("", "11:00",
                "Sunny", "-1*C",
                "", "", "", ""),
        )
        adapter.submitList(list)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}