package ru.maksonic.elmaks.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

/**
 * @author maksonic on 09.05.2022
 */
typealias CitiesList = Flow<Result<List<CityDomain>>>

class FetchCitiesUseCase @Inject constructor(
    private val repository: Repository<CityDomain>
) : BaseUseCase<CitiesList> {
    override fun invoke(vararg args: Any?): CitiesList =
        repository.refreshDataList().transform { tryToSortList ->
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