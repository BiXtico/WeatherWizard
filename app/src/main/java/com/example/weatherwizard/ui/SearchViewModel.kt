package com.example.weatherwizard.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherwizard.network.City
import com.example.weatherwizard.network.WeatherAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val _nextDaysData = MutableLiveData<List<City>>()
    val nextDaysData: LiveData<List<City>>
        get() = _nextDaysData

    private val fetchCitiesJob = Job()
    private val fetchCitiesScope = CoroutineScope(fetchCitiesJob + Dispatchers.IO)

    fun renewSearch(searchable: String){
        fetchCitiesJob.cancel()
        fetchSearchables(searchable)
    }

    private fun fetchSearchables(searchable:String){
        fetchCitiesScope.launch{
            try {
                var data = WeatherAPI.retrofitService.getSearchable(searchable)
                _nextDaysData.value = data.body()
                Log.i("checkAPI","${data?.body()?.size}")
            }catch (t: Throwable){
                Log.i("checkAPI","failed")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchCitiesJob.cancel()
    }
}