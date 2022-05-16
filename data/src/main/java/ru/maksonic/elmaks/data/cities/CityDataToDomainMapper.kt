package ru.maksonic.elmaks.data.cities

import ru.maksonic.elmaks.core.Mapper
import ru.maksonic.elmaks.domain.CityDomain
import javax.inject.Inject

/**
 * @author maksonic on 08.05.2022
 */
class CityDataToDomainMapper @Inject constructor() : Mapper<CityData, CityDomain> {

    override fun mapTo(i: CityData) = CityDomain(
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

    override fun mapFrom(o: CityDomain) = CityData(
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
