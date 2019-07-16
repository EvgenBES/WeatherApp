package bes.tehno.weather_kt.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherDao(
    override val idCity: Int,
    override val date: String,
    override val temp: Double,
    override val tempMin: Double,
    override val tempMax: Double,
    override val humidity: Int,
    override val typeWeather: String,
    override val description: String,
    override val icon: String,
    override val clouds: Double,
    override val windSpeed: Double
): UnitSpecificCurrentWeatherEntry {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}