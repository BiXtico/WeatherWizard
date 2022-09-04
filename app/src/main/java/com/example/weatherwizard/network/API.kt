package com.example.weatherwizard.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

var retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://api.github.com/")
    .build()

interface WeatherService{
    @GET("")
    fun getWeatherInfo(): Call<String>
}

object WeatherAPI {
    val retrofitService : WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }
}

