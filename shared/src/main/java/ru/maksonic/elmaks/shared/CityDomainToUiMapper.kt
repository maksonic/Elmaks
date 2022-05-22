package ru.maksonic.elmaks.shared

import ru.maksonic.elmaks.core.Mapper
import ru.maksonic.elmaks.domain.CityDomain
import javax.inject.Inject

/**
 * @author maksonic on 09.05.2022
 */
class CityDomainToUiMapper @Inject constructor() : Mapper<CityDomain, CityUi> {

    override fun mapTo(i: CityDomain) = CityUi(
        kladrId = i.kladrId,
        name = i.name,
        postalCode = i.postalCode,
        population = i.population.toString(),
        foundationYear = i.foundationYear,
        region = i.region,
        federalDistrict = i.federalDistrict,
        timezone = i.timezone
    )

    override fun mapFrom(o: CityUi) = CityDomain(
        kladrId = o.kladrId,
        name = o.name,
        postalCode = o.postalCode,
        population = o.population.toLong(),
        foundationYear = o.foundationYear,
        region = o.region,
        regionType = "",
        federalDistrict = o.federalDistrict,
        timezone = o.timezone
    )
}