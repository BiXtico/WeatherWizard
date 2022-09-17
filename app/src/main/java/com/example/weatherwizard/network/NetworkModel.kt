package com.example.weatherwizard.network


import com.squareup.moshi.Json

data class CityData(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

data class Current(
    @Json(name = "temp_f")
    val tempF: Double,
    val condition: Condition,
    @Json(name = "wind_mph")
    val windMph: Double,
    val humidity: Int,
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Long
)


data class Forecast(
    val forecastday: List<Forecastday>
)

data class Forecastday(
    val date: String,
    val day: Day,
)

data class Day(
    @Json(name = "maxtemp_f")
    val maxtempF: Double,
    @Json(name = "mintemp_f")
    val mintempF: Double,
    val avghumidity: Long,
    val condition: Condition,
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    @Json(name = "tz_id")
    val tzID: String,
    @Json(name = "localtime_epoch")
    val localtimeEpoch: Long,
    val localtime: String
)

data class City (
    val name: String,
    val country: String,
)

