package bes.tehno.weather_kt.ui.current

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import bes.tehno.weather_kt.app.ForecastApp
import bes.tehno.weather_kt.domain.entity.WeatherDay
import bes.tehno.weather_kt.domain.provider.UnitProvider
import bes.tehno.weather_kt.domain.useCase.GetWeatherUseCase
import bes.tehno.weather_kt.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class CurrentWeatherModel : BaseViewModel() {

    private val progressBar: ObservableBoolean = ObservableBoolean(true)
    private val cityName = ObservableField<String>()
    private val weatherLiveData: MutableLiveData<List<WeatherDay>> = MutableLiveData<List<WeatherDay>>()
    private var weatherObserver: Observer<List<WeatherDay>> = Observer {
        weatherLiveData.value = it
        progressBar.set(false)
    }

    @Inject
    lateinit var weatherUseCase: GetWeatherUseCase

    @Inject
    lateinit var unitProvider: UnitProvider

    init {
        ForecastApp.appComponent.runInject(this)
        launchDataLoad()
    }

    /**
     * Heavy operation that cannot be done in the Main Thread
     */
    private fun launchDataLoad() {
        progressBar.set(true)

        viewModelScope.launch {
            weatherUseCase.getWeather().observeForever(weatherObserver)
        }
    }

    private fun onClearedData() {
        viewModelScope.launch {
            weatherUseCase.getWeather().removeObserver(weatherObserver)
        }
    }

    override fun onCleared() {
        onClearedData()
        super.onCleared()
    }

    fun getWeather(): MutableLiveData<List<WeatherDay>> {
        return weatherLiveData
    }

    fun getProgressBar(): ObservableBoolean {
        return progressBar
    }

    fun getCity(): ObservableField<String> {
        return cityName
    }
}