package com.example.weatherwizard.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherwizard.network.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class HomeViewModel : ViewModel() {

    val _homeData = MutableLiveData<CityData>()
    val homeData: LiveData<CityData>
        get() = _homeData


    private val fetchWeatherDataJob = Job()
    private val fetchWeatherDataScope = CoroutineScope(fetchWeatherDataJob + Dispatchers.IO)


    var searchableText = "London"

    init {
        fetchCurrentData(searchableText)
    }

    fun renewWeatherData(searchable: String){
        fetchWeatherDataJob.cancel()
        fetchCurrentData(searchable)
    }
    private fun fetchCurrentData(searchable: String) {
        fetchWeatherDataScope.launch {
            try {
                var data = WeatherAPI.retrofitService.getWeatherInfo(searchable)
                _homeData.value = data.body()
                Log.i("checkAPI", "${data?.body()?.current?.humidity}")
            } catch (t: Throwable) {
                Log.i("checkAPI", "failed")
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        fetchWeatherDataJob.cancel()
    }


}