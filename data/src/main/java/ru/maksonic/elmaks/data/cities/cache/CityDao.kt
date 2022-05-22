package ru.maksonic.elmaks.data.cities.cache

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.maksonic.elmaks.data.base.BaseDao

/**
 * @Author maksonic on 19.05.2022
 */
@Dao
abstract class CityDao: BaseDao<CityCache> {

    @Query("SELECT * FROM cities_cache")
    abstract override suspend fun fetchCacheList(): List<CityCache>

    @Query("SELECT * FROM cities_cache WHERE kladrId = :itemId")
    abstract override fun fetchCacheItemById(itemId: Long): Flow<CityCache>

    @Query("DELETE FROM cities_cache WHERE kladrId = :itemId")
    abstract override suspend fun deleteCachedItemById(itemId: Long)
}