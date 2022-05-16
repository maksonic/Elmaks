package ru.maksonic.elmaks.domain

import kotlinx.coroutines.flow.Flow

/**
 * @author maksonic on 01.05.2022
 */
typealias CitiesList = Flow<Result<List<CityDomain>>>

interface Repository {
    fun fetchCities(): CitiesList
}