package bes.tehno.weather_kt.data.entity.responce


import com.google.gson.annotations.SerializedName

data class TimeOfData(
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    @SerializedName("dt_txt")
    val dtTxt: String
)