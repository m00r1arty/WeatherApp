//package com.example.weatherapp.ui.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.weatherapp.R
//import com.example.weatherapp.databinding.ListItemBinding
//import com.squareup.picasso.Picasso
//
//class WeatherAdapter(
//    private val listener: Listener?
//) : ListAdapter<WeatherModel, WeatherAdapter.Holder>(Comparator()) {
//
//    // ViewHolder для элементов списка
//    class Holder(view: View, private val listener: Listener?) : RecyclerView.ViewHolder(view) {
//        private val binding = ListItemBinding.bind(view)
//        private var itemTemp: WeatherModel? = null
//
//        // Инициализация обработчика клика на элементе списка
//        init { itemView.setOnClickListener { itemTemp?.let { listener?.onCLickWeatherCard(it) } } }
//
//        // Привязка данных к элементу списка
//        fun bind(item: WeatherModel) = with(binding) {
//            itemTemp = item
//            val currentTemp =
//                if (item.currentTemp.isEmpty()) "${item.maxTemp}°C / ${item.minTemp}°C"
//                else "${item.currentTemp}°C"
//            tvDate.text = item.dateTime
//            tvCondition.text = item.conditionText
//            tvTemp.text = currentTemp
//            Picasso.get().load("https:" + item.imageUrl).into(im)
//        }
//    }
//
//    // Компаратор для определения изменений в списке
//    class Comparator : DiffUtil.ItemCallback<WeatherModel>() {
//        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean = oldItem == newItem
//        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean = oldItem == newItem
//    }
//
//    // Создание ViewHolder и связывание с макетом элемента списка
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//        return Holder(view, listener)
//    }
//
//    // Привязка данных к элементу списка на указанной позиции
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.bind(getItem(position))
//    }
//
//    // Интерфейс слушателя для обработки клика на карточке погоды
//    interface Listener {
//        fun onCLickWeatherCard(item: WeatherModel) {}
//    }
//}
