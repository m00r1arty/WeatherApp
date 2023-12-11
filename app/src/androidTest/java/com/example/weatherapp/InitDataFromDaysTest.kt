package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.domain.model.WeatherModel
import com.example.weatherapp.ui.adapter.WeatherAdapter
import com.example.weatherapp.ui.fragments.DaysFragment
import com.example.weatherapp.ui.viewmodel.MainViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InitDataFromDaysTest {

    private lateinit var daysFragment: DaysFragment
    private lateinit var adapter: WeatherAdapter

    @Before
    fun setUp() {
        daysFragment = mock()
        adapter = mock()

        // Подмена RecyclerView и адаптера в фрагменте
        daysFragment.binding = mock() // Замените на фактическую реализацию биндинга
        daysFragment.adapter = adapter
    }

    @Test
    fun initRecyclerViewTest() {
        // Добавим в тестовый контекст функцию-расширение initRecyclerView
        daysFragment.initRecyclerView()

        verify(daysFragment.binding.recyclerView).layoutManager = LinearLayoutManager(daysFragment.requireActivity())
        verify(daysFragment.binding.recyclerView).adapter = adapter
    }

    @Test
    fun observeWeatherDataTest() {
        // Создание нового экземпляра MainViewModel для каждого теста
        val viewModel = mock<MainViewModel>()

        val liveDataList = MutableLiveData<List<WeatherModel>>()
        val testData = listOf(
            WeatherModel(
            "London",
            "2023.11.23",
            "snow",
            "-3",
            "-6",
            "0",
            "url",
            "15:45"
        )
        )

        // Добавим в тестовый контекст функцию-расширение observeWeatherData
        daysFragment.observeWeatherData()

        whenever(viewModel.liveDataList).thenReturn(liveDataList)

        liveDataList.value = testData

        verify(adapter).submitList(testData.subList(1, testData.size))
    }
}
