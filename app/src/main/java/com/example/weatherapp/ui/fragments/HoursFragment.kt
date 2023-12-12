//package com.example.weatherapp.ui.fragments
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.activityViewModels
//import com.example.weatherapp.databinding.FragmentHoursBinding
//import com.example.weatherapp.ui.viewmodel.MainViewModel
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class HoursFragment : Fragment() {
//    // Привязка для доступа к Views в макете фрагмента
//    internal lateinit var binding: FragmentHoursBinding
//
//    // Адаптер для отображения данных о погоде
//    internal lateinit var adapter: WeatherAdapter
//
//    // ViewModel для взаимодействия с данными
//    internal val viewModel: MainViewModel by activityViewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Инициализация привязки макета фрагмента
//        binding = FragmentHoursBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Инициализация RecyclerView для отображения данных о погодах по часам
//        initRecyclerView()
//
//        // Наблюдение за данными о погоде в ViewModel
//        observeWeatherData()
//    }
//
//    private fun initRecyclerView() = with(binding) {
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        adapter = WeatherAdapter(null)
//        recyclerView.adapter = adapter
//    }
//
//    private fun observeWeatherData() {
//        viewModel.liveDataCurrent.observe(viewLifecycleOwner) {
//            adapter.submitList(getHoursList(it))
//        }
//    }
//
//    private fun getHoursList(weatherItem: WeatherModel): List<WeatherModel> {
//        val hoursArray = JSONArray(weatherItem.hours)
//        return (0 until hoursArray.length()).map { i ->
//            val hourObject = hoursArray.getJSONObject(i)
//            WeatherModel(
//                weatherItem.nameCity,
//                hourObject.getString("time"),
//                hourObject.getJSONObject("condition").getString("text"),
//                hourObject.getString("temp_c").toFloat().toInt().toString(),
//                "",
//                "",
//                hourObject.getJSONObject("condition").getString("icon"),
//                ""
//            )
//        }
//    }
//
////     Создание нового экземпляра HoursFragment
//    companion object {
//        @JvmStatic
//        fun newInstance() = HoursFragment()
//    }
//}
