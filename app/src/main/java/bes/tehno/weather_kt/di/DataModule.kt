package bes.tehno.weather_kt.di

import android.content.Context
import bes.tehno.weather_kt.data.db.AppDatabase
import bes.tehno.weather_kt.data.net.RestService
import bes.tehno.weather_kt.data.net.ServerRepository
import bes.tehno.weather_kt.data.net.ServerRepositoryImpl
import bes.tehno.weather_kt.data.repositories.WeatherRepository
import bes.tehno.weather_kt.data.repositories.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase(context)
    }

    @Singleton
    @Provides
    fun provideServerRepository(serverRepositoryImpl: ServerRepositoryImpl): ServerRepository {
        return serverRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository {
        return weatherRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideRestService(): RestService = RestService()

}