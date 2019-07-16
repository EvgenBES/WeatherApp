package bes.tehno.weather_kt.domain.utils

import bes.tehno.weather_kt.data.entity.WeatherDao
import bes.tehno.weather_kt.domain.entity.WeatherDay
import bes.tehno.weather_kt.domain.entity.WeatherForHour
import bes.tehno.weather_kt.domain.utils.CityConverter.getCityName
import bes.tehno.weather_kt.domain.utils.DateUtils.convertDate
import bes.tehno.weather_kt.domain.utils.DateUtils.getDayOfWeek
import bes.tehno.weather_kt.domain.utils.DateUtils.getShortTime


lateinit var weatherDay: WeatherDay

fun List<WeatherDao>.transformInWeatherDay(): List<WeatherDay> {
    val result = mutableListOf<WeatherDay>()
    var date: String = ""

    this.forEach {
        val shortDate = convertDate(it.date)

        if (it.date.contains("00:00:00") || it.date.contains("03:00:00")) {
            // skip this time
        } else {
            if (date.contains(shortDate)) {
                weatherDay.weatherDayList.add(WeatherForHour(time = getShortTime(it.date), icon = it.icon, temp = it.temp.toInt()))
            } else {
                date = shortDate
                weatherDay = it.transformInWeatherDay()
                weatherDay.weatherDayList.add(WeatherForHour(time = getShortTime(it.date), icon = it.icon, temp = it.temp.toInt()))
                result.add(weatherDay)
            }
        }
    }

    return result
}

fun WeatherDao.transformInWeatherDay(): WeatherDay {
    return WeatherDay(
        city = getCityName(this.idCity),
        date = getDayOfWeek(this.date),
        temp = this.temp.toInt(),
        typeWeather = this.description,
        typeWeatherIcon = this.icon,
        windSpeed = this.windSpeed,
        humidity = this.humidity,
        weatherDayList = ArrayList()
    )
}



//
// weatherDayList = mutableListOf<WeatherForHour>(
// WeatherForHour(time = getShortTime(this.date), icon = this.icon, temp = this.temp)
// )


//
//fun mapDateToWeatherDate(date: String): String {
//
//}

//
//val date: String,
//val temp: Double,
//val typeWeather: String,
//val typeWeatherIcon: String,
//val windSpeed: Double,
//val humidity: Int,
//val weatherDayList: List<WeatherForHour>