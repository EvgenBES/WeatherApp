package bes.tehno.weather_kt.domain.useCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import bes.tehno.weather_kt.data.repositories.WeatherRepository
import bes.tehno.weather_kt.domain.entity.WeatherDay
import bes.tehno.weather_kt.domain.provider.UnitProvider
import bes.tehno.weather_kt.domain.utils.transformInWeatherDay
import javax.inject.Inject

class GetWeatherUseCase
@Inject constructor(private val weatherRepository: WeatherRepository,
                    private val unitProvider: UnitProvider) {

    suspend fun getWeather(): LiveData<List<WeatherDay>> {
        return Transformations.map(weatherRepository.getCurrentWeather(unitProvider.getCityId())) { it.transformInWeatherDay() }
    }

}