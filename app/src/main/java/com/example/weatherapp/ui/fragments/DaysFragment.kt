package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.FragmentDaysBinding
import com.example.weatherapp.ui.viewmodel.MainViewModel

class DaysFragment : Fragment() {
    private lateinit var _binding: FragmentDaysBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Инициализация привязки макета фрагмента
        _binding = FragmentDaysBinding.inflate(inflater, container, false)
        return _binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Инициализация RecyclerView для отображения данных о днях
//        initRecyclerView()
//
//        // Наблюдение за данными о погоде в ViewModel
//        observeWeatherData()
//    }
//
//    // Обработчик клика по карточке погоды
//    override fun onCLickWeatherCard(item: WeatherModel) {
//        // Установка текущей погоды в ViewModel при клике на карточку
//        viewModel.liveDataCurrent.value = item
//    }
//
//    fun initRecyclerView() {
//        adapter = WeatherAdapter(this)
//        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
//        binding.recyclerView.adapter = adapter
//    }
//
//    fun observeWeatherData() {
//        viewModel.liveDataList.observe(viewLifecycleOwner) {
//            // Исключает первый элемент, так как он уже отображается в текущей погоде
//            adapter.submitList(it.subList(1, it.size))
//        }
//    }
//
//    // Создание нового экземпляра DaysFragment

