package bes.tehno.weather_kt.data.net

import bes.tehno.weather_kt.data.entity.responce.WeatherResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RestService {

    private val restApi: RestApi

    companion object {
        private const val URL_API = "http://api.openweathermap.org/data/2.5/"
        private const val API_KEY = "9de243494c0b295cca9337e1e96b00e2"
    }

    init {
        val requestInterceptor = Interceptor { chain ->

            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("appid", API_KEY)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(requestInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL_API)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient.build())
            .build()

        restApi = retrofit.create(RestApi::class.java)
    }

    fun getWeather(cityID: Int): Deferred<WeatherResponse> {
        return restApi.getWeather(cityID)
    }
}