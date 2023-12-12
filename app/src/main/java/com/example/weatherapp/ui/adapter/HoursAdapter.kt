package com.example.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemBinding
import com.example.weatherapp.domain.model.HoursWeatherItemModel
import com.squareup.picasso.Picasso

class HoursAdapter: ListAdapter<HoursWeatherItemModel, HoursAdapter.Holder>(Comparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)

        fun bind(hoursWeather: HoursWeatherItemModel) {
            binding.apply {
                tvDateItem.text = hoursWeather.time
                tvConditionItem.text = hoursWeather.conditionText
                tvTempItem.text = "${hoursWeather.currentTemp}°C"

                Picasso.get()
                    .load("https:" + hoursWeather.imageUrl)
                    .placeholder(R.drawable.ic_test)
                    .error(R.drawable.ic_error)
                    .into(imWeatherItem)
            }
        }

    }


    // Компаратор для определения изменений в списке
    class Comparator : DiffUtil.ItemCallback<HoursWeatherItemModel>() {
        override fun areItemsTheSame(oldItem: HoursWeatherItemModel, newItem: HoursWeatherItemModel): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: HoursWeatherItemModel, newItem: HoursWeatherItemModel): Boolean = oldItem == newItem
    }
}