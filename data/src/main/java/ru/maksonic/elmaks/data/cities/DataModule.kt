package ru.maksonic.elmaks.data.cities

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.maksonic.elmaks.data.cities.cache.CitiesCacheDataSource
import ru.maksonic.elmaks.data.cities.cache.CityCacheToDataMapper
import ru.maksonic.elmaks.data.cities.cloud.CityCloudToDataMapper
import ru.maksonic.elmaks.data.cities.cloud.FakeCloudDataSource
import ru.maksonic.elmaks.data.common.AssetsReader
import ru.maksonic.elmaks.data.common.JsonConverter
import ru.maksonic.elmaks.domain.CityDomain
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
    fun provideAssetsReader(@ApplicationContext context: Context): AssetsReader =
        AssetsReader.Reader(context)

    @Singleton
    @Provides
    fun provideJsonConverter(assetsReader: AssetsReader): JsonConverter =
        JsonConverter.Converter(assetsReader)

    @Singleton
    @Provides
    fun provideCitiesRepository(
        baseCloudDataSource: FakeCloudDataSource,
        baseCacheDataSource: CitiesCacheDataSource,
        cloudMapper: CityCloudToDataMapper,
        cacheMapper: CityCacheToDataMapper,
        dataToDomainMapper: CityDataToDomainMapper
    ): Repository<CityDomain> = CitiesRepository(
        baseCloudDataSource = baseCloudDataSource,
        baseCacheDataSource = baseCacheDataSource,
        cloudMapper = cloudMapper,
        cacheMapper = cacheMapper,
        dataToDomainMapper = dataToDomainMapper
    )
}