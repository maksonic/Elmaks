package ru.maksonic.elmaks.data.cities.cache

import ru.maksonic.elmaks.core.Mapper
import ru.maksonic.elmaks.data.cities.CityData
import javax.inject.Inject

/**
 * @Author maksonic on 19.05.2022
 */
class CityCacheToDataMapper @Inject constructor() : Mapper<CityCache, CityData> {

    override fun mapTo(i: CityCache) = CityData(
        kladrId = i.kladrId,
        name = i.name,
        postalCode = i.postalCode,
        population = i.population,
        foundationYear = i.foundationYear,
        region = i.region,
        regionType = i.regionType,
        federalDistrict = i.federalDistrict,
        timezone = i.timezone
    )

    override fun mapFrom(o: CityData) = CityCache(
        kladrId = o.kladrId,
        name = o.name,
        postalCode = o.postalCode,
        population = o.population,
        foundationYear = o.foundationYear,
        region = o.region,
        regionType = o.regionType,
        federalDistrict = o.federalDistrict,
        timezone = o.timezone
    )
}
