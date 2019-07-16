package bes.tehno.weather_kt.data.entity


interface UnitSpecificCurrentWeatherEntry {
    val idCity: Int
    val date: String
    val temp: Double
    val tempMin: Double
    val tempMax: Double
    val humidity: Int
    val typeWeather: String
    val description: String
    val icon: String
    val clouds: Double
    val windSpeed: Double
}