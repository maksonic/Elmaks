package ru.maksonic.elmaks.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

/**
 * @Author maksonic on 19.05.2022
 */
class FetchCityDetailsUseCase @Inject constructor(
    private val repository: Repository<CityDomain>
) : BaseUseCase<Flow<Result<CityDomain>>, Long> {
    override fun invoke(args: Long?): Flow<Result<CityDomain>> =
        repository.findItemById(args ?: 0).transform { tryFindItemById ->
            tryFindItemById.onSuccess { itemDomain ->
                val regionName: String =
                    if (itemDomain.regionType.contains("обл", ignoreCase = true)
                        || itemDomain.regionType.contains("край", ignoreCase = true)
                    ) {
                        itemDomain.region.plus(" ${itemDomain.regionType}")
                    } else {
                        itemDomain.regionType.plus(" ${itemDomain.region}")
                    }
                emit(Result.success(itemDomain.copy(region = regionName)))
            }

            tryFindItemById.onFailure { throwable ->
                emit(Result.failure(throwable))
            }
        }
}
