package bes.tehno.weather_kt.data.repositories

import androidx.lifecycle.LiveData
import bes.tehno.weather_kt.data.entity.WeatherDao

interface WeatherRepository {
    suspend fun getCurrentWeather(city: Int): LiveData<List<WeatherDao>>
}