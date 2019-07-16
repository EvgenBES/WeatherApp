package bes.tehno.weather_kt.data.net

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bes.tehno.weather_kt.data.entity.responce.WeatherResponse
import java.io.IOException
import javax.inject.Inject

class ServerRepositoryImpl @Inject constructor(
    private val apiService: RestService
) : ServerRepository {

    private val _downloadedCurrentWeather = MutableLiveData<WeatherResponse>()
    override val downloadedCurrentWeather: LiveData<WeatherResponse>
        get() = _downloadedCurrentWeather


    override suspend fun fetchCurrentWeather(cityID: Int) {
        try {
            val fetchedCurrentWeather = apiService.getWeather(cityID).await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        } catch (e: IOException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}