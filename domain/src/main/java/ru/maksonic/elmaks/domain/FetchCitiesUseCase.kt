package ru.maksonic.elmaks.domain

import kotlinx.coroutines.flow.transform
import javax.inject.Inject

/**
 * @Author maksonic on 20.05.2022
 */
class FetchCitiesUseCase @Inject constructor(
    private val repository: Repository<CityDomain>
) : BaseUseCase<CitiesList, Nothing> {
    override fun invoke(args: Nothing?): CitiesList =
        repository.fetchCitiesList().transform { tryToSortList ->
            tryToSortList.onSuccess { cities ->
                val filteredCities = cities
                    .filter { it.name.isNotEmpty() }
                    .sortedBy { it.name }
                emit(Result.success(filteredCities))
            }
            tryToSortList.onFailure { throwable ->
                emit(Result.failure(throwable))
            }
        }
}