package com.example.weatherwizard.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherwizard.network.*
import kotlinx.coroutines.*


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

    fun renewWeatherData(searchable: String?) {
        searchable?.let {
            searchableText = searchable
            fetchCurrentData(searchable)
        }
    }

    private fun fetchCurrentData(searchable: String) {
        fetchWeatherDataScope.launch {
            try {
                val data = WeatherAPI.retrofitService.getWeatherInfo(searchable)
                _comingDays.value = data.body()?.asDayDomainModel()
                _homeData.value = data.body()?.asWeatherDataDomainModel()
                Log.i("checkAPI", "${data.body()?.forecast?.forecastday?.size}")
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