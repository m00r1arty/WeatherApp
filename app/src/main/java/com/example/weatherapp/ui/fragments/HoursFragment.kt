package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.databinding.FragmentHoursBinding
import com.example.weatherapp.ui.adapter.HoursAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoursFragment : Fragment() {

    private lateinit var _binding: FragmentHoursBinding
    private val hoursAdapter = HoursAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoursBinding.inflate(inflater, container, false).apply {
            hoursRecycler.adapter = hoursAdapter
        }

        return _binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}

//
//    private fun initRecyclerView() = with(binding) {
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        adapter = WeatherAdapter(null)
//        recyclerView.adapter = adapter
//    }
////
//    private fun observeWeatherData() {
//        viewModel.liveDataCurrent.observe(viewLifecycleOwner) {
//            hoursAdapter.submitList(getHoursList(it))
//        }
//    }
//
//
////     Создание нового экземпляра HoursFragment
//    companion object {
//        @JvmStatic
//        fun newInstance() = HoursFragment()
//    }
//}
