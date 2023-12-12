package com.example.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemBinding
import com.example.weatherapp.domain.model.DaysWeatherItemModel
import com.squareup.picasso.Picasso

class DaysAdapter: ListAdapter<DaysWeatherItemModel, DaysAdapter.Holder>(Comparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)

        fun bind(daysWeather: DaysWeatherItemModel) {
            binding.apply {
                tvDateItem.text = daysWeather.dateTime
                tvConditionItem.text = daysWeather.conditionText
                tvTempItem.text = "${daysWeather.maxTemp}°C/${daysWeather.minTemp}°C"

                Picasso.get()
                    .load("https:" + daysWeather.imageUrl)
                    .placeholder(R.drawable.ic_test)
                    .error(R.drawable.ic_error)
                    .into(imWeatherItem)
            }
        }

    }


    // Компаратор для определения изменений в списке
    class Comparator : DiffUtil.ItemCallback<DaysWeatherItemModel>() {
        override fun areItemsTheSame(oldItem: DaysWeatherItemModel, newItem: DaysWeatherItemModel): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: DaysWeatherItemModel, newItem: DaysWeatherItemModel): Boolean = oldItem == newItem
    }
}