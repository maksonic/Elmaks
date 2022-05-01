package ru.maksonic.elmaks.domain

/**
 * @author makosnic on 01.05.2022
 */
interface CitiesRepository {
    suspend fun fetchCitiesList(): List<CityDomain>
}