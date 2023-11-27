package com.example.weatherapp.domain.mainextensions

import com.example.weatherapp.domain.Constants.tList
import com.example.weatherapp.domain.mainextensions.LocationManager.checkLocation
import com.example.weatherapp.domain.mainextensions.ParsingData.requestWeatherData
import com.example.weatherapp.ui.DialogManager
import com.example.weatherapp.ui.adapter.ViewPageAdapter
import com.example.weatherapp.ui.fragments.MainFragment
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

object Extensions {

    fun MainFragment.updateCurrentCard() = with(binding) {
        viewModel.liveDataCurrent.observe(viewLifecycleOwner) { currentData ->
            val maxMinTemp = "${currentData.maxTemp}°C / ${currentData.minTemp}°C"
            val currentTemp = if (currentData.currentTemp.isEmpty()) maxMinTemp else "${currentData.currentTemp}°C"
            with(tvCity) { text = currentData.city }
            with(tvDate) { text = currentData.time }
            Picasso.get().load("https:" + currentData.imageUrl).into(imWeather)
            with(tvCurrentTemp) { text = currentTemp }
            with(tvCondition) { text = currentData.condition }
            with(tvMaxMin) { text = if (currentData.currentTemp.isEmpty()) "" else maxMinTemp }
        }
    }

    fun MainFragment.init() = with(binding) {
        fusedLocationClient = getFusedLocationProviderClient(requireContext())
        viewPage.adapter = ViewPageAdapter(requireActivity(), fList)
        TabLayoutMediator(tabLayout, viewPage) { tab, position -> tab.text = tList[position] }.attach()
        ibSync.setOnClickListener { tabLayout.selectTab(tabLayout.getTabAt(0)); checkLocation() }
        ibSearch.setOnClickListener { showSearchDialog() }
    }

    private fun MainFragment.showSearchDialog() {
        DialogManager.searchByNameDialog(requireContext(), object : DialogManager.Listener {
            override fun onClick(name: String?) {
                name?.let { requestWeatherData(it) }
            }
        })
    }
}
