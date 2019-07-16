package bes.tehno.weather_kt.domain.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun convertDate(date: String): String {
        val mDate: Date = convertSimpleDataFormat(date)
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(mDate)
    }

    fun getDayOfWeek(date: String): String {
        val mDate = convertSimpleDataFormat(date)
        val cal = Calendar.getInstance()
        cal.time = mDate
        return "${cal.get(Calendar.DAY_OF_MONTH)} ${getMonthStringRu(cal.get(Calendar.MONTH))}"
    }

    fun getShortTime(date: String): String {
        val mDate: Date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(date)
        val test =
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(mDate)

        return test
    }


    private fun convertSimpleDataFormat(date: String): Date {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)
    }

    private fun getMonthStringRu(month: Int): String {
        return when(month){
            0 -> "Января"
            1 -> "Февраля"
            2 -> "Марта"
            3 -> "Апреля"
            4 -> "Майя"
            5 -> "Июня"
            6 -> "Июля"
            7 -> "Августа"
            8 -> "Сентября"
            9 -> "Октября"
            10 -> "Ноября"
            11 -> "Декабря"
            else -> "Неизвестный"
        }
    }
}