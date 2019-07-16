package bes.tehno.weather_kt.data.repositories

import androidx.lifecycle.LiveData
import bes.tehno.weather_kt.data.db.AppDatabase
import bes.tehno.weather_kt.data.entity.WeatherDao
import bes.tehno.weather_kt.data.entity.responce.WeatherResponse
import bes.tehno.weather_kt.data.entity.transformList
import bes.tehno.weather_kt.data.net.ServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val appServer: ServerRepository,
    serverRepository: ServerRepository
) : WeatherRepository {

    private var lastRequestOnServer: Long = 0
    private var lastCity: Int = 0

    init {
        serverRepository.apply {
            downloadedCurrentWeather.observeForever { newCurrentWeather ->
                persistFetchedCurrentWeather(newCurrentWeather)
            }
        }
    }

    private fun persistFetchedCurrentWeather(newCurrentWeather: WeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            appDatabase.currentWeatherDao().deleteAll()
            appDatabase.currentWeatherDao().insert(newCurrentWeather.transformList())
        }
    }

    override suspend fun getCurrentWeather(city: Int): LiveData<List<WeatherDao>> {
        return withContext(Dispatchers.IO) {
            initWeatherData(city)
            return@withContext appDatabase.currentWeatherDao().getWeather()
        }
    }

    private suspend fun initWeatherData(city: Int) {
        if (lastRequestOnServer < Calendar.getInstance().time.time || lastCity != city) {
            appServer.fetchCurrentWeather(city)
            lastRequestOnServer = Calendar.getInstance().time.time + TimeUnit.MINUTES.toMillis(30)
            lastCity = city
        }
    }
}