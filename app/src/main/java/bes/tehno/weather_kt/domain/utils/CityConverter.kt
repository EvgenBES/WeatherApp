package bes.tehno.weather_kt.domain.utils

object CityConverter {

    fun getCityId(valueOf: UnitSystem): Int {
        return when (valueOf) {
            UnitSystem.MINSK -> 625144
            UnitSystem.BREST -> 629634
            UnitSystem.HRODNA -> 627904
            UnitSystem.GOMEL -> 627907
            UnitSystem.VITEBSC -> 620127
            UnitSystem.MOGILEV -> 625665
            UnitSystem.BOBRUISK -> 630468
            UnitSystem.BARANOVICH -> 630429
            UnitSystem.NOVOPOLODSK -> 624784
            UnitSystem.PINSK -> 623549
            UnitSystem.BORISOV -> 630376
        }
    }
    fun getCityName(idCity: Int): String {
        return when (idCity) {
            629634    -> "Брест"
            627904    -> "Гродно"
            627907    -> "Гомель"
            620127    -> "Витебск"
            625665    -> "Могилёв"
            630468    -> "Бобруйск"
            630429    -> "Барановичи"
            624784    -> "Новополоцк"
            623549    -> "Пинск"
            630376    -> "Борисов"
            else -> "Минск"
        }
    }
}