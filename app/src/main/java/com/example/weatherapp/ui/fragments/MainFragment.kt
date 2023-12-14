package com.example.weatherapp.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.ui.DialogManager
import com.example.weatherapp.ui.adapter.DaysAdapter
import com.example.weatherapp.ui.viewmodel.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var adapter: DaysAdapter
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var isButtonCToFPressed = false

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

        binding.apply {
            daysRecycler.layoutManager = LinearLayoutManager(activity)
            adapter = DaysAdapter()
            daysRecycler.adapter = adapter
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
            ibSync.setOnClickListener { checkLocation(); isButtonCToFPressed = false }
            ibSearch.setOnClickListener { showSearchDialog(); isButtonCToFPressed = false }
            ibCToF.setOnClickListener {
                if (isButtonCToFPressed) {
                    Toast.makeText(
                        context,
                        getString(R.string.celsius_to_fahrenheit_converted),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    checkLocation(isFahrenheit = true)
                    isButtonCToFPressed = true
                }
            }
        }

        viewModel.daysList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.currentWeather.observe(viewLifecycleOwner) { weatherData ->
            binding.apply {
                tvCity.text = weatherData.nameCity
                tvDateTime.text = weatherData.dateTime
                tvCurrentTemp.text =getString(
                    if (isButtonCToFPressed) R.string.current_temp_f else R.string.current_temp,
                    weatherData.currentTemp
                )
                tvCondition.text = weatherData.conditionText
                tvMaxMin.text = getString(
                    if (isButtonCToFPressed) R.string.max_min_temp_f else R.string.max_min_temp,
                    weatherData.maxTemp,
                    weatherData.minTemp
                )
                Picasso.get()
                    .load(getString(R.string.https) + weatherData.imageUrl)
                    .placeholder(R.drawable.ic_test)
                    .error(R.drawable.ic_error)
                    .into(imWeather)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkLocation()
    }

    private fun checkLocation(isFahrenheit: Boolean = false) {
        if (isLocationEnabled()) {
            getLocation(isFahrenheit)
        } else {
            DialogManager.locationSettingsDialog(requireContext()) {
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
        }
    }

    @SuppressLint("MissingPermission") // Added because without this annotation AS show error but project works correctly
    private fun getLocation(isFahrenheit: Boolean = false) {
        val cancelToken = CancellationTokenSource()
        if (isLocationPermissionGranted()) {
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancelToken.token
            ).addOnCompleteListener {
                it.result?.let { location ->
                    val locationString = "${location.latitude},${location.longitude}"
                    val currentWeather = if (isFahrenheit) viewModel::getCurrentWeatherCardFahrenheit else viewModel::getCurrentWeatherCard
                    val updateDaysList = if (isFahrenheit) viewModel::updateDaysListFahrenheit else viewModel::updateDaysList

                    currentWeather(locationString)
                    updateDaysList(locationString)
                }
            }
        }
    }

    private fun isLocationPermissionGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun isLocationEnabled() =
        (activity?.getSystemService(Context.LOCATION_SERVICE) as?
                LocationManager)?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true

    private fun checkPermission() {
        if (!isLocationPermissionGranted()) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            val message = if (isGranted) "Permission granted!" else "Permission denied"
            Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showSearchDialog() {
        DialogManager.searchByNameDialog(requireContext()) { name ->
            name?.let { nameCity ->
                viewModel.getCurrentWeatherCard(nameCity)
                viewModel.updateDaysList(nameCity)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
