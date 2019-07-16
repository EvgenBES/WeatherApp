package bes.tehno.weather_kt.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bes.tehno.weather_kt.data.db.dao.CurrentWeatherDao
import bes.tehno.weather_kt.data.entity.WeatherDao


@Database(
    entities = [WeatherDao::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object{
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "weather.db")
                .build()
    }
}