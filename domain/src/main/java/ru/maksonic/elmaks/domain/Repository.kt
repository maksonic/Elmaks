package ru.maksonic.elmaks.domain

import kotlinx.coroutines.flow.Flow
import ru.maksonic.elmaks.core.DomainObject

/**
 * @author maksonic on 01.05.2022
 */
interface Repository<T: DomainObject> {
    fun fetchDataList(): Flow<Result<List<T>>>
    fun fetchCloudDataList(): Flow<Result<List<T>>>
    fun findItemById(itemId: Long): Flow<Result<T>>
}