package com.example.weatherapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.ui.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentWeather.observe(viewLifecycleOwner) {weatherData ->
            binding.tvCity.text = weatherData.nameCity
            binding.tvDate.text = weatherData.dateTime
            binding.tvCurrentTemp.text = weatherData.currentTemp.toString()
            binding.tvCondition.text = weatherData.conditionText
            binding.tvMaxMin.text = "${weatherData.maxTemp.toString()}°C/${weatherData.minTemp.toString()}°C"
            Log.e("LOGGGG", weatherData.currentTemp.toString())
            Picasso.get()
                .load("https:" + weatherData.imageUrl)
                .placeholder(R.drawable.ic_test)
                .error(R.drawable.ic_error)
                .into(binding.imWeather)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCurrentWeatherCard()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}


//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        // Проверка разрешения на местоположение
//        checkPermission()
//
//        // Инициализация компонентов и установка обработчиков
//        init()
//
//        // Обновление информации о текущей погоде
//        updateCurrentCard()
//    }
//    private fun init() = with(binding) {
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
//        viewPage.adapter = ViewPageAdapter(requireActivity(), fList)
//        TabLayoutMediator(tabLayout, viewPage) { tab, position -> tab.text = Constants.tList[position] }.attach()
//        ibSync.setOnClickListener { tabLayout.selectTab(tabLayout.getTabAt(0)); checkLocation() }
//        ibSearch.setOnClickListener { showSearchDialog() }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        // Проверка доступности местоположения при возвращении на фрагмент
//        checkLocation()
//    }
//
//    private fun showSearchDialog() {
//        DialogManager.searchByNameDialog(requireContext()) { name ->
//            name?.let { requestWeatherData(it) }
//        }
//    }
//
//    private fun updateCurrentCard() = with(binding) {
//        viewModel.liveDataCurrent.observe(viewLifecycleOwner) { currentData ->
//            val maxMinTemp = "${currentData.maxTemp}°C / ${currentData.minTemp}°C"
//            val currentTemp = if (currentData.currentTemp.isEmpty()) maxMinTemp else "${currentData.currentTemp}°C"
//            tvCity.text = currentData.nameCity
//            tvDate.text = currentData.dateTime
//            Picasso.get().load("https:" + currentData.imageUrl).into(imWeather)
//            tvCurrentTemp.text = currentTemp
//            tvCondition.text = currentData.conditionText
//            with(tvMaxMin) { text = if (currentData.currentTemp.isEmpty()) "" else maxMinTemp }
//        }
//    }

    // Создание нового экземпляра MainFragment
