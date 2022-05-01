package ru.maksonic.elmaks.domain

/**
 * @author makosnic on 01.05.2022
 */
data class CityDomain(
    val name: String,
    val postalCode: Long,
    val population: Long,
    val foundationYear: Int,
    val region: String,
    val regionType: String,
    val federalDistrict: String,
    val timezone: String
)