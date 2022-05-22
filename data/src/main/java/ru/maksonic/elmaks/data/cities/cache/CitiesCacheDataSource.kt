package ru.maksonic.elmaks.data.cities.cache

import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.elmaks.core.di.IoDispatcher
import ru.maksonic.elmaks.core.store.ResourceProvider
import ru.maksonic.elmaks.data.base.BaseCacheDataSource
import javax.inject.Inject

/**
 * @Author maksonic on 19.05.2022
 */
class CitiesCacheDataSource @Inject constructor(
    dao: CityDao,
    rp: ResourceProvider,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseCacheDataSource.Base<CityCache>(dao, rp, dispatcher)