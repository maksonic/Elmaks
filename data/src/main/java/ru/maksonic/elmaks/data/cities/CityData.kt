package ru.maksonic.elmaks.data.cities

import ru.maksonic.elmaks.core.DataObject

/**
 * @author maksonic on 01.05.2022
 */
data class CityData(
    val kladrId: Long,
    val name: String,
    val postalCode: Long,
    val population: Long,
    val foundationYear: Int,
    val region: String,
    val regionType: String,
    val federalDistrict: String,
    val timezone: String
) : DataObject
