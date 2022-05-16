package ru.maksonic.elmaks.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import ru.maksonic.elmaks.core.data.JsonConverter
import ru.maksonic.elmaks.core.di.IoDispatcher
import ru.maksonic.elmaks.core.store.ResourceProvider
import ru.maksonic.elmaks.data.cities.CitiesRepository
import ru.maksonic.elmaks.data.cities.CityDataToDomainMapper
import ru.maksonic.elmaks.domain.Repository
import javax.inject.Singleton

/**
 * @author maksonic on 01.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Singleton
    @Provides
    fun provideCitiesRepository(
        mapper: CityDataToDomainMapper,
        json: Json,
        jsonConverter: JsonConverter,
        rp: ResourceProvider,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): Repository =
        CitiesRepository(mapper, json, jsonConverter, rp,dispatcher)
}