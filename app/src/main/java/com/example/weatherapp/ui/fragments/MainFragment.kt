package com.example.weatherapp.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import com.android.volley.Request
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.domain.Constants.API_KEY
import com.example.weatherapp.domain.Constants.BASE_URL
import com.example.weatherapp.ui.DialogManager
import com.example.weatherapp.ui.adapter.ViewPageAdapter
import com.example.weatherapp.ui.viewmodel.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
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
    private lateinit var fusedLocationClient: FusedLocationProviderClient
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
    }

    private fun updateCurrentCard() = with(binding) {
        viewModel.liveDataCurrent.observe(viewLifecycleOwner) { currentData ->
            val maxMinTemp = "${currentData.maxTemp}°C / ${currentData.minTemp}°C"
            val currentTemp = if (currentData.currentTemp.isEmpty()) maxMinTemp else "${currentData.currentTemp}°C"
            tvCity.text = currentData.city
            tvDate.text = currentData.time
            Picasso.get().load("https:" + currentData.imageUrl).into(imWeather)
            tvCurrentTemp.text = currentTemp
            tvCondition.text = currentData.condition
            tvMaxMin.text = if (currentData.currentTemp.isEmpty()) "" else maxMinTemp
        }
    }

    override fun onResume() {
        super.onResume()
        checkLocation()
    }

    private fun init() = with(binding) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        val adapter = ViewPageAdapter(requireActivity(), fList)
        viewPage.adapter = adapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            tab.text = tList[position]
        }.attach()
        ibSync.setOnClickListener {
            tabLayout.selectTab(tabLayout.getTabAt(0))
            checkLocation()
        }
        ibSearch.setOnClickListener {
            showSearchDialog()
        }
    }

    private fun showSearchDialog() {
        DialogManager.searchByNameDialog(requireContext(), object : DialogManager.Listener {
            override fun onClick(name: String?) {
                name?.let { requestWeatherData(it) }
            }
        })
    }

    private fun checkLocation() {
        if (isLocationEnabled()) {
            getLocation()
        } else {
            DialogManager.locationSettingsDialog(requireContext(), object : DialogManager.Listener {
                override fun onClick(name: String?) {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }
            })
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        return locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val cancelToken = CancellationTokenSource()
        if (isLocationPermissionGranted()) {
            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cancelToken.token)
                .addOnCompleteListener { result ->
                    result.result?.let {
                        requestWeatherData("${it.latitude},${it.longitude}")
                    }
                }
        }
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            val message = if (isGranted) "Permission granted!" else "Permission denied"
            Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
        }
    }

    private fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermission() {
        if (!isLocationPermissionGranted()) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun requestWeatherData(city: String) {
        val url = buildWeatherApiUrl(city)
        val queue = Volley.newRequestQueue(requireContext())
        val request = StringRequest(
            Request.Method.GET,
            url,
            { result -> parseWeatherData(result) },
            { error -> handleWeatherRequestError(error) }
        )
        queue.add(request)
    }

    private fun buildWeatherApiUrl(city: String): String {
        return "$BASE_URL$API_KEY&q=$city&days=6&aqi=no&alerts=no"
    }

    private fun handleWeatherRequestError(error: VolleyError) {
        Log.e("MyLog", "Error $error")
        // Обработайте ошибку, покажите тост или обновите интерфейс соответственно
    }

    private fun parseWeatherData(result: String) {
        val mainObject = JSONObject(result)
        val list = parseDays(mainObject)
        parseCurrentData(mainObject, list[0])
    }

    private fun parseDays(mainObject: JSONObject): List<WeatherModel> {
        val list = mutableListOf<WeatherModel>()
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
                day.getJSONObject("day").getString("maxtemp_c")
                    .toFloat().toInt().toString(),
                day.getJSONObject("day").getString("mintemp_c")
                    .toFloat().toInt().toString(),
                day.getJSONObject("day").getJSONObject("condition")
                    .getString("icon"),
                day.getJSONArray("hour").toString()
            )
            list.add(item)
        }
        viewModel.liveDataList.value = list
        return list
    }

    private fun parseCurrentData(mainObject: JSONObject, weatherItem: WeatherModel) {
        val item = WeatherModel(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").getString("last_updated"),
            mainObject.getJSONObject("current")
                .getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").getString("temp_c")
                .toFloat().toInt().toString(),
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
