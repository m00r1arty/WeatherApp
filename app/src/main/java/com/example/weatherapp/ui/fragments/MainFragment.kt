package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.domain.mainextensions.Extensions.init
import com.example.weatherapp.domain.mainextensions.Extensions.updateCurrentCard
import com.example.weatherapp.domain.mainextensions.LocationManager.checkLocation
import com.example.weatherapp.domain.mainextensions.LocationManager.checkPermission
import com.example.weatherapp.ui.viewmodel.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class MainFragment : Fragment() {
    internal val fList by lazy {
        listOf(
            HoursFragment.newInstance(),
            DaysFragment.newInstance()
        )
    }

    internal lateinit var fusedLocationClient: FusedLocationProviderClient
    internal lateinit var pLauncher: ActivityResultLauncher<String>
    internal lateinit var binding: FragmentMainBinding
    internal val viewModel: MainViewModel by activityViewModels()

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

    override fun onResume() {
        super.onResume()
        checkLocation()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
