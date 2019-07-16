package bes.tehno.weather_kt.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bes.tehno.weather_kt.data.entity.WeatherDao
import retrofit2.http.DELETE

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<WeatherDao>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: WeatherDao)

    @Query("SELECT * FROM weather")
    fun getWeather(): LiveData<List<WeatherDao>>

    @Query("DELETE FROM weather")
    fun deleteAll()
}