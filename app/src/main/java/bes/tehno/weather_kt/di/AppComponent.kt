package bes.tehno.weather_kt.di

import bes.tehno.weather_kt.app.ForecastApp
import bes.tehno.weather_kt.ui.current.CurrentWeatherModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, DataModule::class, DomainModule::class] )
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ForecastApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: ForecastApp)

    fun runInject(currentWeatherModel: CurrentWeatherModel)

}