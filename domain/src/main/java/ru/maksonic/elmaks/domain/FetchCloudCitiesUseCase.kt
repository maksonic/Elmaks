package ru.maksonic.elmaks.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

/**
 * @author maksonic on 09.05.2022
 */
typealias CitiesList = Flow<Result<List<CityDomain>>>

class FetchCloudCitiesUseCase @Inject constructor(
    private val repository: Repository<CityDomain>
) : BaseUseCase<CitiesList, Nothing> {
    override fun invoke(args: Nothing?): CitiesList =
        repository.fetchCloudDataList().transform { tryFetchCloudList ->
            tryFetchCloudList.onSuccess { cities ->
                val filteredCities = cities
                    .filter { it.name.isNotEmpty() }
                    .shuffled()
                emit(Result.success(filteredCities))
            }
            tryFetchCloudList.onFailure { throwable ->
                emit(Result.failure(throwable))
            }
        }
}
