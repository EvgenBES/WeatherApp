package bes.tehno.weather_kt.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    open fun onResume() {}

    open fun onPause() {}

    open fun onViewReady() {}
}