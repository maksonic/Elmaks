package ru.maksonic.elmaks.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.maksonic.elmaks.core.data.AssetsReader
import ru.maksonic.elmaks.core.data.JsonConverter
import ru.maksonic.elmaks.core.store.AppDataStore
import ru.maksonic.elmaks.core.store.AppThemeSetting
import ru.maksonic.elmaks.core.store.ResourceProvider
import javax.inject.Singleton

/**
 * @author maksonic on 01.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider =
        ResourceProvider.Base(context)

    @Singleton
    @Provides
    fun provideDataStore(): AppDataStore = AppDataStore.ThemeSetting()

    @Singleton
    @Provides
    fun provideAppTheme(
        @ApplicationContext context: Context,
        dataStore: AppDataStore
    ): AppThemeSetting =
        AppThemeSetting.Base(context, dataStore)

    @Singleton
    @Provides
    fun provideAssetsReader(@ApplicationContext context: Context): AssetsReader =
        AssetsReader.Reader(context)

    @Singleton
    @Provides
    fun provideJsonConverter(assetsReader: AssetsReader): JsonConverter =
        JsonConverter.Converter(assetsReader)
}