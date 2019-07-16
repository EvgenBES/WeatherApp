package bes.tehno.weather_kt.di

import android.content.Context
import bes.tehno.weather_kt.app.ForecastApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: ForecastApp): Context = app.applicationContext

}