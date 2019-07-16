package bes.tehno.weather_kt.data.entity

import bes.tehno.weather_kt.data.entity.responce.TimeOfData
import bes.tehno.weather_kt.data.entity.responce.WeatherResponse


fun WeatherResponse.transformList(): List<WeatherDao> {
    val result = mutableListOf<WeatherDao>()
    val id = this.city.id
    this.list.forEach { result.add(it.transform(id)) }
    return result
}

fun TimeOfData.transform(id: Int): WeatherDao {
    return WeatherDao(
        idCity = id,
        date = dtTxt,
        temp = main.temp,
        tempMin = main.tempMin,
        tempMax = main.tempMax,
        humidity = main.humidity,
        typeWeather = weather[0].main,
        description = weather[0].description,
        icon = weather[0].icon,
        clouds = clouds.all,
        windSpeed = wind.speed
    )
}