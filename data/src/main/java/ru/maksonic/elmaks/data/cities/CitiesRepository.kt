package ru.maksonic.elmaks.data.cities

import ru.maksonic.elmaks.data.base.AbstractRepository
import ru.maksonic.elmaks.data.cities.cache.CitiesCacheDataSource
import ru.maksonic.elmaks.data.cities.cache.CityCache
import ru.maksonic.elmaks.data.cities.cache.CityCacheToDataMapper
import ru.maksonic.elmaks.data.cities.cloud.CityCloud
import ru.maksonic.elmaks.data.cities.cloud.CityCloudToDataMapper
import ru.maksonic.elmaks.data.cities.cloud.FakeCloudDataSource
import ru.maksonic.elmaks.domain.CityDomain
import javax.inject.Inject

/**
 * @author maksonic on 01.05.2022
 */
class CitiesRepository @Inject constructor(
    baseCloudDataSource: FakeCloudDataSource,
    baseCacheDataSource: CitiesCacheDataSource,
    cloudMapper: CityCloudToDataMapper,
    cacheMapper: CityCacheToDataMapper,
    dataToDomainMapper: CityDataToDomainMapper
) : AbstractRepository<CityCloud, CityCache, CityData, CityDomain>(
    baseCloudDataSource = baseCloudDataSource,
    baseCacheDataSource = baseCacheDataSource,
    cloudMapper = cloudMapper,
    cacheMapper = cacheMapper,
    dataToDomainMapper = dataToDomainMapper
)