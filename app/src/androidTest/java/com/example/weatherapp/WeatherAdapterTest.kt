package com.example.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.ui.adapter.WeatherAdapter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WeatherAdapterTest {

    private lateinit var adapter: WeatherAdapter

    @Mock
    private lateinit var mockListener: WeatherAdapter.Listener

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        adapter = WeatherAdapter(mockListener)
    }

    @Test
    fun getItemCount_shouldReturnItemCount() {
        val items = listOf(
            WeatherModel("1", "", "Condition 1", "25", "20", "30", "https://example.com/image1", ""),
            WeatherModel("2", "", "Condition 2", "22", "18", "26", "https://example.com/image2", ""),
            WeatherModel("3", "", "Condition 3", "28", "24", "32", "https://example.com/image3", "")
        )
        adapter.submitList(items)

        assertEquals(items.size, adapter.itemCount)
    }

    @Test
    fun onCreateViewHolder_shouldInflateView() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val parent = Mockito.mock(ViewGroup::class.java)
        val viewType = 0

        try {
            // Заменяем создание holder на настоящий LayoutInflater
            val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
            val holder = adapter.onCreateViewHolder(parent, viewType)
            assertEquals(itemView.javaClass, holder.itemView.javaClass)
        } catch (e: Exception) {
            // Выводим информацию о возможной ошибке
            e.printStackTrace()
            throw e
        }
    }


    @Test
    fun onBindViewHolder_shouldBindData() {
        val mockParent = Mockito.mock(ViewGroup::class.java)
        val viewType = 0
        val holder = adapter.onCreateViewHolder(mockParent, viewType)

        val item = WeatherModel("1", "", "Condition 1", "25", "20", "30", "https://example.com/image1", "5")
        adapter.onBindViewHolder(holder, 0)

        holder.bind(item)

        Mockito.verify(mockListener).onCLickWeatherCard(item)
    }


}
