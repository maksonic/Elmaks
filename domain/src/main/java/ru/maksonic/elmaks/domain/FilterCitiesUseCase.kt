package ru.maksonic.elmaks.domain

import javax.inject.Inject

/**
 * @author maksonic on 09.05.2022
 */
interface FilterCitiesUseCase {
  //  suspend fun filterFromAtoZ(): List<CityDomain>
  //  suspend fun filterFromZtoA(): List<CityDomain>
  //  suspend fun filterPostalCodeAscending(): List<CityDomain>
  //  suspend fun filterPostalCodeDescending(): List<CityDomain>


    class Base @Inject constructor(private val repository: Repository) : FilterCitiesUseCase {

     /*   override suspend fun filterFromAtoZ() = repository.fetchCitiesList()//.sortedBy { it.name }

        override suspend fun filterFromZtoA() =
            repository.fetchCitiesList()//.sortedByDescending { it.name }

        override suspend fun filterPostalCodeAscending() =
            repository.fetchCitiesList()//.sortedBy { it.postalCode }

        override suspend fun filterPostalCodeDescending() =
            repository.fetchCitiesList()//.sortedByDescending { it.postalCode }*/
    }
}