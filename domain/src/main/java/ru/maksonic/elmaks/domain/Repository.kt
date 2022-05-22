package ru.maksonic.elmaks.domain

import kotlinx.coroutines.flow.Flow
import ru.maksonic.elmaks.core.DomainObject

/**
 * @author maksonic on 01.05.2022
 */
interface Repository<T: DomainObject> {
    fun fetchCitiesList(): Flow<Result<List<T>>>
    fun fetchCloudCitiesList(): Flow<Result<List<T>>>
    fun findItemById(itemId: Long): Flow<Result<T>>
}