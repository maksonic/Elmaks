package ru.maksonic.elmaks.data.base

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.elmaks.core.CacheObject
import ru.maksonic.elmaks.core.di.IoDispatcher
import ru.maksonic.elmaks.core.store.ResourceProvider
import ru.maksonic.elmaks.data.R
import ru.maksonic.elmaks.data.common.EmptyCacheException

/**
 * @Author maksonic on 19.05.2022
 */
interface BaseCacheDataSource<T> {
    fun fetchCacheList(): DataList<T>
    fun fetchCacheItemById(itemId: Long): Flow<Result<T>>
    suspend fun insertCacheList(list: List<T>)

    abstract class Base<T : CacheObject>(
        private val dao: BaseDao<T>,
        private val rp: ResourceProvider,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : BaseCacheDataSource<T> {

        override fun fetchCacheList() = flow {
            val cacheList = dao.fetchCacheList()
            if (cacheList.isEmpty()) {
                emit(
                    Result.failure(
                        EmptyCacheException(
                            rp.getString(R.string.error_failed_cache_fetching)
                        )
                    )
                )
            } else {
                emit(Result.success(cacheList))
            }
        }.flowOn(dispatcher)

        override fun fetchCacheItemById(itemId: Long): Flow<Result<T>> = flow {
            try {
                dao.fetchCacheItemById(itemId).collect { foundedItem ->
                    emit(Result.success(foundedItem))
                }
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }

        override suspend fun insertCacheList(list: List<T>) {
            try {
                if (list.isNotEmpty()) {
                    dao.deleteAllCachedList(list)
                    dao.insertAll(list)
                }
            } catch (e: Exception) {
                Log.e("!!", "ERROR -> ${e.localizedMessage}")
            }
        }
    }
}