package ru.maksonic.elmaks.domain

import ru.maksonic.elmaks.core.DomainObject

/**
 * @author maksonic on 01.05.2022
 */
data class CityDomain(
    val kladrId: Long,
    val name: String,
    val postalCode: Long,
    val population: Long,
    val foundationYear: Int,
    val region: String,
    val regionType: String,
    val federalDistrict: String,
    val timezone: String
) : DomainObject