package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.ui.viewmodel.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
//    // Список фрагментов для использования в PagerAdapter
//    internal val fList by lazy {
//        listOf(
//            HoursFragment.newInstance(),
//            DaysFragment.newInstance()
//        )
//    }

    // Клиент для получения данных о местоположении
    internal lateinit var fusedLocationClient: FusedLocationProviderClient

    // Лаунчер для запроса разрешения местоположения
    internal lateinit var pLauncher: ActivityResultLauncher<String>

    // Привязка для доступа к Views в макете фрагмента
    internal lateinit var binding: FragmentMainBinding

    // ViewModel для взаимодействия с данными
    internal val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Инициализация привязки макета фрагмента
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
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
    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
