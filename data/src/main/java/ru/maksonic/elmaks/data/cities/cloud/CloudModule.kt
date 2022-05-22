package ru.maksonic.elmaks.data.cities.cloud

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import ru.maksonic.elmaks.core.di.IoDispatcher
import ru.maksonic.elmaks.core.store.ResourceProvider
import ru.maksonic.elmaks.data.common.JsonConverter
import javax.inject.Singleton

/**
 * @Author maksonic on 19.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CloudModule {

    @Singleton
    @Provides
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Singleton
    @Provides
    fun provideCitiesCloudDataSource(
        json: Json,
        jsonConverter: JsonConverter,
        rp: ResourceProvider,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): FakeCloudDataSource = FakeCloudDataSource(json, jsonConverter, rp, dispatcher)

}