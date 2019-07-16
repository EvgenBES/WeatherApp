package bes.tehno.weather_kt.app

import android.app.Activity
import android.app.Application
import bes.tehno.weather_kt.di.AppComponent
import bes.tehno.weather_kt.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ForecastApp : Application(), HasActivityInjector {

    companion object {
        lateinit var instance: ForecastApp
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    init {
        instance = this
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)
    }


}