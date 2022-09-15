package com.example.weatherwizard.ui

import android.util.Log
import android.widget.Toast
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

    private val _homeData = MutableLiveData<WeatherData>()
    val homeData: LiveData<WeatherData>
        get() = _homeData

    private val _comingDays = MutableLiveData<List<NextDay>>()
    val comingDays: LiveData<List<NextDay>>
        get() = _comingDays


    private val fetchWeatherDataJob = Job()
    private val fetchWeatherDataScope = CoroutineScope(fetchWeatherDataJob + Dispatchers.Main)


    var searchableText = "London"

    init {
        fetchCurrentData(searchableText)
    }

    fun renewWeatherData(searchable: String) {

        searchableText = searchable
        fetchCurrentData(searchable)
    }

    private fun fetchCurrentData(searchable: String) {
        fetchWeatherDataScope.launch {
            try {
                var data = WeatherAPI.retrofitService.getWeatherInfo(searchable)
                _homeData.value = data.body()?.asWeatherDataDomainModel()
                _comingDays.value = data.body()?.asDayDomainModel()
                Log.i("checkAPI", "${data.body()?.location?.localtime}")
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