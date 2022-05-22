package ru.maksonic.elmaks.data.cities.cloud

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.maksonic.elmaks.core.di.IoDispatcher
import ru.maksonic.elmaks.core.store.ResourceProvider
import ru.maksonic.elmaks.data.base.BaseCloudDataSource
import ru.maksonic.elmaks.data.common.JsonConverter
import javax.inject.Inject
import kotlin.random.Random

/**
 * @Author maksonic on 19.05.2022
 */
class FakeCloudDataSource @Inject constructor(
    private val json: Json,
    jsonConverter: JsonConverter,
    rp: ResourceProvider,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseCloudDataSource.Base<CityCloud>(jsonConverter, rp, dispatcher) {
    private companion object {
        private const val CITIES = "cities.json"
    }

    override val jsonFileName: String = CITIES
    override fun cloudList(rawString: String): List<CityCloud> {
        //Simulation of different answer options from server (just for example)
        val fakeResult = Random.nextInt(0, 10)

        return if (fakeResult % 2 == 1)
            json.decodeFromString(rawString)
        else
            emptyList()
    }
}