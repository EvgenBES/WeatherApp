package bes.tehno.weather_kt.domain.entity

data class WeatherDay (
    val city: String,
    val date: String,
    val temp: Int,
    val typeWeather: String,
    val typeWeatherIcon: String,
    val windSpeed: Double,
    val humidity: Int,
    val weatherDayList: ArrayList<WeatherForHour>
)