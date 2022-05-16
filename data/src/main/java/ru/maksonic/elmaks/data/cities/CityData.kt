package ru.maksonic.elmaks.data.cities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author maksonic on 01.05.2022
 */
@Serializable
data class CityData(
    @SerialName("kladr_id")
    val kladrId: Long,
    @SerialName("city")
    val name: String,
    @SerialName("postal_code")
    val postalCode: Long,
    @SerialName("population")
    val population: Long,
    @SerialName("foundation_year")
    val foundationYear: Int,
    @SerialName("region")
    val region: String,
    @SerialName("region_type")
    val regionType: String,
    @SerialName("federal_district")
    val federalDistrict: String,
    @SerialName("timezone")
    val timezone: String
)
