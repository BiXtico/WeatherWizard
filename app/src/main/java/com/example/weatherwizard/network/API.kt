package com.example.weatherwizard.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val URL = "https://api.weatherapi.com/v1/"
private const val key = "42f2af07bfae4fe4a98232721221709"
private const val days = 3
private const val aqiResponse = "no"
private const val alertStatus = "no"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private var retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URL)
    .build()

interface WeatherService {
    @GET("forecast.json?key=$key&days=$days&aqi=$aqiResponse&alerts=$alertStatus")
    suspend fun getWeatherInfo(@Query("q") city: String): Response<CityData>

    @GET("search.json?key=$key")
    suspend fun getSearchable(@Query("q") searchable: String): Response<List<City>>
}

object WeatherAPI {
    val retrofitService: WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }
}


//interface WeatherService {
//    @Headers( "Authentication: key=e90e7437ca04421a80b141901220309",
//        "numOfDays: days=3",
//        "aqi: aqi=no",
//        "alerts: alerts=no")
//    @GET("forecast.json?")
//    fun getWeatherInfo(@Header("q") cityName: String): Call<CityData>
//
//    @Headers( "Authentication: key=e90e7437ca04421a80b141901220309")
//    @GET("search.json?")
//    fun getCities(): Call<List<City>>
//}