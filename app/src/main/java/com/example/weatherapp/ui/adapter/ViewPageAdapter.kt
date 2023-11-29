package com.example.weatherapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageAdapter(
    fa: FragmentActivity,
    private val list: List<Fragment>
) : FragmentStateAdapter(fa) {

    // Возвращает общее количество фрагментов в списке
    override fun getItemCount(): Int = list.size

    // Создает и возвращает фрагмент для указанной позиции
    override fun createFragment(position: Int): Fragment = list[position]
}
