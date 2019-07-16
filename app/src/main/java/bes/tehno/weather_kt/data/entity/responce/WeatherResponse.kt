package bes.tehno.weather_kt.data.entity.responce


data class WeatherResponse(
    val list: List<TimeOfData>,
    val city: City
)