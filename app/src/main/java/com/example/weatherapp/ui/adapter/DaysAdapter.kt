//package com.example.weatherapp.ui.adapter
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.weatherapp.R
//import com.example.weatherapp.domain.model.HoursWeatherItemModel
//import com.squareup.picasso.Picasso
//
//class DaysAdapter(
//    private var hoursList: List<HoursWeatherItemModel>
//): RecyclerView.Adapter<DaysAdapter.HoursViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_hours, parent, false)
//        return HoursViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
//        val listItem = hoursList[position]
//        holder.bind(listItem)
//    }
//
//    override fun getItemCount() = hoursList.size
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun updateFlashSaleList(hoursList: List<HoursWeatherItemModel>) {
//        this.hoursList = hoursList
//        this.notifyDataSetChanged()
//    }
//
//    class HoursViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val time: TextView = itemView.findViewById(R.id.tvDateItem)
//        private val condition: TextView = itemView.findViewById(R.id.tvConditionItem)
//        private val temp: TextView = itemView.findViewById(R.id.tvTempItem)
//        private val icon: ImageView = itemView.findViewById(R.id.imWeatherItem)
//
//        fun bind(hoursWeather: HoursWeatherItemModel) {
//            time.text = hoursWeather.time
//            condition.text = hoursWeather.conditionText
//            temp.text = "${hoursWeather.currentTemp}°C"
//            Picasso.get()
//                .load("https:" + hoursWeather.imageUrl)
//                .placeholder(R.drawable.ic_test)
//                .error(R.drawable.ic_error)
//                .into(icon)
//        }
//
//    }
//}