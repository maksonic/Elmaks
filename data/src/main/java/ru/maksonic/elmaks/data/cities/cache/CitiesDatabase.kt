package ru.maksonic.elmaks.data.cities.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.maksonic.elmaks.core.store.ResourceProvider
import ru.maksonic.elmaks.data.R

/**
 * @Author maksonic on 19.05.2022
 */
private const val DB_VERSION = 1

interface DatabaseName {
    val name: String

    class City(rp: ResourceProvider) : DatabaseName {
        override val name = rp.getString(R.string.db_name)
    }
}

@Database(entities = [CityCache::class], version = DB_VERSION, exportSchema = false)
abstract class CitiesDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
