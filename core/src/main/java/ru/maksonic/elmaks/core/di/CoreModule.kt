package ru.maksonic.elmaks.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.maksonic.elmaks.core.store.*
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
    fun provideColorGenerator(): ColorGenerator = ColorGenerator.Generation()

    @Singleton
    @Provides
    fun provideKeyStore(): KeyStore = KeyStore.PassedDataKey()
}