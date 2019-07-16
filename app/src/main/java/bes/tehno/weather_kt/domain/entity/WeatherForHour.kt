package bes.tehno.weather_kt.domain.entity

data class WeatherForHour(
    val time: String,
    val icon: String,
    val temp: Int
)