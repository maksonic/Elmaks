package ru.maksonic.elmaks.domain

import kotlinx.coroutines.flow.transform
import javax.inject.Inject

/**
 * @author maksonic on 09.05.2022
 */
class FetchCitiesUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<CitiesList> {

    override fun invoke(): CitiesList = repository.fetchCities().transform { oldCitiesFlow ->
        oldCitiesFlow.onSuccess { cities ->
            val filteredCities = cities
                .filter { it.name.isNotEmpty() }
                .sortedBy { it.name }
            emit(Result.success(filteredCities))
        }
        oldCitiesFlow.onFailure { throwable ->
            emit(Result.failure(throwable))
        }
    }
}