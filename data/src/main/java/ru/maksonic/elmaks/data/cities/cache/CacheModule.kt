package ru.maksonic.elmaks.data.cities.cache

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.elmaks.core.di.IoDispatcher
import ru.maksonic.elmaks.core.store.ResourceProvider
import javax.inject.Singleton

/**
 * @Author maksonic on 19.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDatabaseName(rp: ResourceProvider): DatabaseName = DatabaseName.City(rp)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context, db: DatabaseName) = Room
        .databaseBuilder(context, CitiesDatabase::class.java, db.name)
        .build()

    @Singleton
    @Provides
    fun provideCategoryDao(db: CitiesDatabase): CityDao = db.cityDao()

    @Singleton
    @Provides
    fun provideCacheCityDataSource(
        dao: CityDao,
        rp: ResourceProvider,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): CitiesCacheDataSource = CitiesCacheDataSource(dao, rp, dispatcher)
}