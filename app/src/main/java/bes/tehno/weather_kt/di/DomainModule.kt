package bes.tehno.weather_kt.di

import bes.tehno.weather_kt.domain.provider.UnitProvider
import bes.tehno.weather_kt.domain.provider.UnitProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideUnitProvider(unitProviderImpl: UnitProviderImpl): UnitProvider {
        return unitProviderImpl
    }
}