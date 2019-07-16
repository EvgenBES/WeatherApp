package bes.tehno.weather_kt.data.entity.responce


data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)