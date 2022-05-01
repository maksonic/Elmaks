package ru.maksonic.elmaks.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.maksonic.elmaks.core.di.IoDispatcher
import ru.maksonic.elmaks.domain.CitiesRepository
import ru.maksonic.elmaks.domain.CityDomain
import javax.inject.Inject

/**
 * @author makosnic on 01.05.2022
 */
class BaseCitiesRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CitiesRepository {

    override suspend fun fetchCitiesList(): List<CityDomain> = withContext(dispatcher) {
        /*listOf(
            CityDomain(1, "Москва", "1"),
            CityDomain(2, "Санкт-Петербург", "2"),
            CityDomain(3, "Краснодар", "3"),
            CityDomain(4, "Сочи", "6"),
            CityDomain(5, "Челябинск", "33"),
            CityDomain(6, "Омск", "32"),
            CityDomain(7, "Норильск", "11"),
            CityDomain(8, "Кострома", "23"),
            CityDomain(9, "Екатеринбург", "0"),
            CityDomain(10, "Волгоград", "4"),
            CityDomain(11, "Нижний Новгород", "007"),
            CityDomain(12, "Калининград", "9"),
            CityDomain(13, "Геленджик", "55"),
            CityDomain(14, "Самара", "20"),
            CityDomain(15, "Йошкар-Ола", "06"),
            CityDomain(16, "Великий Новгород", "90"),
            CityDomain(17, "Владивосток", "2000"),
            CityDomain(18, "Звенигород", "234"),
            CityDomain(19, "Железноводск", "78"),
            CityDomain(20, "Казань", "50"),
        )*/
        emptyList<CityDomain>()
    }.apply { delay(3000L) }
}