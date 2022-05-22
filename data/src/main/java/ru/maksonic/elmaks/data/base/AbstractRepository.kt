package ru.maksonic.elmaks.data.base

import kotlinx.coroutines.flow.*
import ru.maksonic.elmaks.core.*
import ru.maksonic.elmaks.domain.Repository

/**
 * @Author maksonic on 19.05.2022
 */
typealias DataList<T> = Flow<Result<List<T>>>

abstract class AbstractRepository<
        A : CloudObject,
        B : CacheObject,
        C : DataObject,
        D : DomainObject>(
    private val baseCloudDataSource: BaseCloudDataSource.Base<A>,
    private val baseCacheDataSource: BaseCacheDataSource.Base<B>,
    private val cacheMapper: Mapper<B, C>,
    private val cloudMapper: Mapper<A, C>,
    private val dataToDomainMapper: Mapper<C, D>
) : Repository<D> {

    override fun fetchDataList(): DataList<D> =
        baseCacheDataSource.fetchCacheList().transform { cacheRequest ->
            cacheRequest.onSuccess { cacheList ->
                val dataList = cacheMapper.mapToList(cacheList)
                val domainList = dataToDomainMapper.mapToList(dataList)
                emit(Result.success(domainList))
            }
            cacheRequest.onFailure {
                fetchCloudDataList().collect { cloudRequest ->
                    cloudRequest.onSuccess { cloudLists ->
                        emit(Result.success(cloudLists))
                    }
                    cloudRequest.onFailure { throwable ->
                        emit(Result.failure(throwable))
                    }
                }
            }
        }

    override fun fetchCloudDataList(): DataList<D> =
        baseCloudDataSource.fetchCloudList().transform { cloudRequest ->
            cloudRequest.onSuccess { cloudList ->
                val dataList = cloudMapper.mapToList(cloudList)
                val cacheList = cacheMapper.mapFromList(dataList)
                baseCacheDataSource.insertCacheList(cacheList)
                val domainList = dataToDomainMapper.mapToList(dataList)
                emit(Result.success(domainList))
            }
            cloudRequest.onFailure { throwable ->
                emit(Result.failure(throwable))
            }
        }

    override fun findItemById(itemId: Long): Flow<Result<D>> =
        baseCacheDataSource.fetchCacheItemById(itemId).transform { tryFoundItem ->
            tryFoundItem.onSuccess { cacheItem ->
                val dataItem = cacheMapper.mapTo(cacheItem)
                val domainItem = dataToDomainMapper.mapTo(dataItem)
                emit(Result.success(domainItem))
            }
            tryFoundItem.onFailure { throwable ->
                emit(Result.failure(throwable))
            }
        }
}