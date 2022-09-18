package com.example.weatherwizard.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherwizard.databinding.SearchCardBinding
import com.example.weatherwizard.network.City

class RecycleViewAdapter(val clickListener: CityListener): ListAdapter<City, RecycleViewAdapter.CityViewHolder>(CityDiffCallback()) {


    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder.from(parent)
    }

    class CityViewHolder private constructor (val binding: SearchCardBinding) :RecyclerView.ViewHolder(binding.root){
        val cityName: TextView = binding.cityResult

        fun bind(
            item: City,
            clickListener: CityListener
        ) {
            cityName.text = item.name +" - "+ item.country
            binding.city = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): CityViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SearchCardBinding.inflate(layoutInflater,parent,false)
                return CityViewHolder(binding)
            }
        }
    }
}

class CityListener(val clickListener: (cityName: String) -> Unit) {
    fun onClick(city: City) = clickListener(city.name)
}

class CityDiffCallback :
    DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }
}