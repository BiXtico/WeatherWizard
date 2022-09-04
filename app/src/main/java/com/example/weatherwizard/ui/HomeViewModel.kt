package com.example.weatherwizard.ui

import androidx.lifecycle.ViewModel
import com.example.weatherwizard.network.WeatherAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel


    fun getCurrentWeather(){
        WeatherAPI.retrofitService.getWeatherInfo().enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}