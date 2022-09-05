package com.example.weatherwizard.network


data class WeatherData(
    val time: String,
    val cityName: String,
    val dayName: String,
    val date: String,
    val icon: String,
    val temperature: String,
    val windSpeed: String,
    val humidityPercentage: String,
    val condition: String
)

data class NextDay(
    val icon: String,
    val temperatureHighest: String,
    val temperatureLowest: String,
    val dayName: String,
)

