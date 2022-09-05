package com.example.weatherwizard.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherwizard.network.CityData
import com.example.weatherwizard.network.WeatherAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val output = MutableLiveData<String>()

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather(){
        WeatherAPI.retrofitService.getWeatherInfo("London").enqueue(object: Callback<CityData> {
            override fun onFailure(call: Call<CityData>, t: Throwable) {
                Log.i("checkAPI","failed")
            }
            override fun onResponse(call: Call<CityData>, response: Response<CityData>) {
                Log.i("checkAPI","there is a response")
            }
        })
    }
}