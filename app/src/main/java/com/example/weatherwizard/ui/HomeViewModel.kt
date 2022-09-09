package com.example.weatherwizard.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherwizard.network.City
import com.example.weatherwizard.network.CityData
import com.example.weatherwizard.network.WeatherAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val _homeData = MutableLiveData<CityData>()
    val homeData :LiveData<CityData>
    get()= _homeData

    val _nextDaysData = MutableLiveData<List<City>>()
    val nextDaysData :LiveData<List<City>>
    get() = _nextDaysData

    var searchableText = "London"

    init {
        getCurrentWeather(searchableText)
        getSearchable()
    }

    private fun getCurrentWeather(searchable:String){
        WeatherAPI.retrofitService.getWeatherInfo(searchable).enqueue(object: Callback<CityData> {
            override fun onFailure(call: Call<CityData>, t: Throwable) {
                Log.i("checkAPI","failed")
            }
            override fun onResponse(call: Call<CityData>, response: Response<CityData>) {
                Log.i("checkAPI","${response.body()?.current?.humidity}")
            }
        })
    }
    private fun getSearchable(){
        WeatherAPI.retrofitService.getSearchable("London").enqueue(object: Callback<List<City>> {
            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                Log.i("checkAPI","failed")
            }
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                Log.i("checkAPI","${response.body()?.size}")
            }
        })
    }
}