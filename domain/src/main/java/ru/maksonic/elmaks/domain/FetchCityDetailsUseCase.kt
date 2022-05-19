package ru.maksonic.elmaks.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author maksonic on 19.05.2022
 */
class FetchCityDetailsUseCase @Inject constructor(
    private val repository: Repository<CityDomain>
)  {
    fun invoke(id: Long): Flow<Result<CityDomain>> = repository.findItemById(id)
}