package ru.maksonic.elmaks.data.cities

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.maksonic.elmaks.core.data.FakeDataException
import ru.maksonic.elmaks.core.di.IoDispatcher
import ru.maksonic.elmaks.core.store.ResourceProvider
import ru.maksonic.elmaks.core.data.JsonConverter
import ru.maksonic.elmaks.data.R
import ru.maksonic.elmaks.domain.CitiesList
import ru.maksonic.elmaks.domain.CityDomain
import ru.maksonic.elmaks.domain.Repository
import javax.inject.Inject
import kotlin.random.Random

/**
 * @author maksonic on 01.05.2022
 */
class CitiesRepository @Inject constructor(
    private val mapper: CityDataToDomainMapper,
    private val json: Json,
    private val jsonConverter: JsonConverter,
    private val rp: ResourceProvider,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : Repository {
    private companion object {
        private const val CITIES = "cities.json"
        private const val FAKE_DELAY = 3000L
    }

    override fun fetchCitiesList(): CitiesList = flow<Result<List<CityDomain>>> {
        delay(FAKE_DELAY)
        val conversion = jsonConverter.convertAssertJsonToString(CITIES)

        conversion.onSuccess { rawString ->
            val dataCitiesList = json.decodeFromString<List<CityData>>(rawString)

            val domainCitiesList = mapper.mapToList(dataCitiesList)
            //Simulation of different answer options from server (just for example)
            Random.nextInt(0, 10).let {
                if (it % 2 == 1)
                    emit(Result.success(domainCitiesList))
                else
                    emit(
                        Result.failure(
                            FakeDataException(rp.getString(R.string.error_failed_loading))
                        )
                    )
            }
            conversion.onFailure { throwable ->
                emit(Result.failure(throwable))
            }
        }
    }.flowOn(dispatcher)
}