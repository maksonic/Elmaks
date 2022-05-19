package ru.maksonic.elmaks.data.cities.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.maksonic.elmaks.core.CacheObject

/**
 * @Author maksonic on 19.05.2022
 */
@Entity(tableName = "cities_cache")
data class CityCache(
    @PrimaryKey
    val kladrId: Long,
    val name: String = "",
    val postalCode: Long,
    val population: Long,
    val foundationYear: Int,
    val region: String = "",
    val regionType: String = "",
    val federalDistrict: String = "",
    val timezone: String = ""
) : CacheObject
