package bes.tehno.weather_kt.domain.provider

import android.content.Context
import bes.tehno.weather_kt.domain.utils.CityConverter
import bes.tehno.weather_kt.domain.utils.UnitSystem
import javax.inject.Inject


class UnitProviderImpl @Inject constructor(context:  Context): PreferenceProvider(context), UnitProvider {

    companion object{
        const val UNIT_SYSTEM = "CUSTOM_LOCATION"
    }

    override fun getCityId(): Int {
        val selectedName = preferences.getString(UNIT_SYSTEM, UnitSystem.MINSK.name)
        return CityConverter.getCityId(UnitSystem.valueOf(selectedName!!))
    }
}