package com.example.weatherapp.ui.fragments

import android.Manifest
import android.location.Location
import com.example.weatherapp.ui.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.ui.DialogManager
import com.example.weatherapp.ui.adapter.DaysAdapter
import com.example.weatherapp.ui.viewmodel.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var resultLauncher: ActivityResultLauncher<String>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: DaysAdapter
    private lateinit var locationManager: LocationManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        adapter = DaysAdapter(viewModel)
        locationManager = LocationManager(fusedLocationClient,this)

        checkPermission()

        binding.apply {
            daysRecycler.layoutManager = LinearLayoutManager(activity)
            daysRecycler.adapter = adapter
            syncButton.setOnClickListener { locationManager.checkLocation { handleLocationResult(it) } }
            searchButton.setOnClickListener { showSearchDialog() }
            celsiusToFahrenheitButton.setOnClickListener {
                viewModel.isFahrenheit = !viewModel.isFahrenheit
                viewModel.invalidateData()
            }
        }

        viewModel.daysList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.currentWeather.observe(viewLifecycleOwner) { weatherData ->
            binding.apply {
                cityTextView.text = weatherData.nameCity
                dateTimeTextView.text = weatherData.dateTime
                currentTempTextView.text = getString(
                    if (viewModel.isFahrenheit) R.string.current_temp_f else R.string.current_temp,
                    weatherData.currentTemp
                )
                conditionTextView.text = weatherData.conditionText
                maxMinTextView.text = getString(
                    if (viewModel.isFahrenheit) R.string.max_min_temp_f else R.string.max_min_temp,
                    weatherData.maxTemp,
                    weatherData.minTemp
                )
                Picasso.get()
                    .load(getString(R.string.https) + weatherData.imageUrl)
                    .placeholder(R.drawable.ic_test)
                    .error(R.drawable.ic_error)
                    .into(weatherIcon)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        locationManager.checkLocation { handleLocationResult(it) }
    }

    private fun handleLocationResult(location: Location) {
        val locationString = "${location.latitude},${location.longitude}"
        viewModel.updateWeather(locationString)
    }

    private fun checkPermission() {
        if (!locationManager.isLocationPermissionGranted()) {
            permissionListener()
            resultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun permissionListener() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            val message = if (isGranted) "Permission granted!" else "Permission denied!"
            Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showSearchDialog() {
        DialogManager.searchByNameDialog(requireContext()) { name ->
            name?.let { nameCity ->
                viewModel.updateWeather(nameCity)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
