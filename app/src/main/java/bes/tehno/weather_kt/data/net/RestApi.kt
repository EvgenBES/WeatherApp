package bes.tehno.weather_kt.data.net

import bes.tehno.weather_kt.data.entity.responce.WeatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

//    forecast
//    ?id=625144&appid=9de243494c0b295cca9337e1e96b00e2
//    &lang=ru
//    &units=metric

    @GET("forecast")
    fun getWeather(
        @Query("id") cityId: Int,
        @Query("lang") language: String = "ru",
        @Query("units") temperature: String = "metric"
    ): Deferred<WeatherResponse>
}