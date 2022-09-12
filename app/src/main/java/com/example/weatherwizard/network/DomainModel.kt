package com.example.weatherwizard.network

import java.text.SimpleDateFormat
import java.util.*


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

fun CityData.asWeatherDataDomainModel() = WeatherData(
    date = location.localtime.substring(0, 10).asDateFormat(),
    cityName = location.name,
    dayName = location.localtime.substring(0, 10).asDayFormat(),
    time = location.localtime.substring(10, 16),
    icon = current.condition.icon,
    temperature = current.tempF.toString(),
    windSpeed = current.windMph.toString(),
    humidityPercentage = current.humidity.toString(),
    condition = "It's " + current.condition.text
)

fun CityData.asDayDomainModel(dayNum: Int) = NextDay(
    icon = forecast.forecastday[dayNum].day.condition.icon,
    temperatureHighest = "/" +forecast.forecastday[dayNum].day.maxtempF.toString(),
    temperatureLowest = forecast.forecastday[dayNum].day.mintempF.toString(),
    dayName = forecast.forecastday[dayNum].date.asDayFormat()
)

fun String.asDayFormat() : String{
    val inFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val date: Date = inFormat.parse(this)
    val outFormat: SimpleDateFormat = SimpleDateFormat("EEEE")
    val dateInLetters: String = outFormat.format(date)
    return dateInLetters
}
fun String.asDateFormat() : String{
    val inFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val date: Date = inFormat.parse(this)
    val outFormat: SimpleDateFormat = SimpleDateFormat("EEEE, dd MMM YYYY")
    val dateInLetters: String = outFormat.format(date)
    return dateInLetters
}
