package com.example.weatherapp.ui.fragments

import android.Manifest
import com.android.volley.Request
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.domain.ApiConstants.API_KEY
import com.example.weatherapp.domain.ApiConstants.BASE_URL
import com.example.weatherapp.domain.isPermissionGranted
import com.example.weatherapp.ui.adapter.ViewPageAdapter
import com.example.weatherapp.ui.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import org.json.JSONObject

class MainFragment : Fragment() {

    private val fList by lazy {
        listOf(
            HoursFragment.newInstance(),
            DaysFragment.newInstance()
        )
    }
    private val tList = listOf("Hours", "Days")
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()
        updateCurrentCard()
        requestWeatherData("Kirovo-Chepetsk")
    }

    private fun updateCurrentCard() = with(binding) {
        viewModel.liveDataCurrent.observe(viewLifecycleOwner) {
            val maxMinTemp = "${it.maxTemp}°C/${it.minTemp}°C"
            val currentTemp = "${it.currentTemp}°C"
            tvCity.text = it.city
            tvDate.text = it.time
            Picasso.get().load("https:" + it.imageUrl).into(imWeather)
            tvCurrentTemp.text = currentTemp
            tvCondition.text = it.condition
            tvMaxMin.text = maxMinTemp

        }
    }

    private fun init() {
        binding.apply {
            val adapter = ViewPageAdapter(requireActivity(), fList)
            viewPage.adapter = adapter
            TabLayoutMediator(tabLayout, viewPage) { tab, position ->
                tab.text = tList[position]
            }.attach()
        }
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            val message = if (isGranted) "Разрешение дано!" else "Разрешение не дано!"
            Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun requestWeatherData(city: String) {
        val url = "$BASE_URL$API_KEY&q=$city&days=5&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(requireContext())
        val request = StringRequest(
            Request.Method.GET,
            url,
            { result -> parseWeatherData(result)},
            { error -> Log.d("MyLog", "Error $error") }
        )
        queue.add(request)
    }

    private fun parseWeatherData(result: String) {
        val mainObject = JSONObject(result)
        val list = parseDays(mainObject)
        parseCurrentData(mainObject, list[0])

    }
    private fun parseDays(mainObject: JSONObject) : List<WeatherModel> {
        val list = ArrayList<WeatherModel>()
        val name = mainObject.getJSONObject("location").getString("name")
        val daysArray = mainObject.getJSONObject("forecast")
            .getJSONArray("forecastday")
        for (i in 0 until daysArray.length()) {
            val day = daysArray[i] as JSONObject
            val item = WeatherModel(
                name,
                day.getString("date"),
                day.getJSONObject("day").getJSONObject("condition")
                    .getString("text"),
                "",
                day.getJSONObject("day").getString("maxtemp_c"),
                day.getJSONObject("day").getString("mintemp_c"),
                day.getJSONObject("day").getJSONObject("condition")
                    .getString("icon"),
                day.getJSONArray("hour").toString()
            )
            list.add(item)
        }
        return list
    }

    private fun parseCurrentData(mainObject: JSONObject, weatherItem: WeatherModel) {
        val item = WeatherModel(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").getString("last_updated"),
            mainObject.getJSONObject("current")
                .getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").getString("temp_c"),
            weatherItem.maxTemp,
            weatherItem.minTemp,
            mainObject.getJSONObject("current")
                .getJSONObject("condition").getString("icon"),
            weatherItem.hours,
        )
        viewModel.liveDataCurrent.value = item
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
