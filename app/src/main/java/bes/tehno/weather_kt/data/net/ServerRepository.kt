package bes.tehno.weather_kt.data.net

import androidx.lifecycle.LiveData
import bes.tehno.weather_kt.data.entity.responce.WeatherResponse

interface ServerRepository {
    val downloadedCurrentWeather: LiveData<WeatherResponse>
    suspend fun fetchCurrentWeather(cityID: Int)
}