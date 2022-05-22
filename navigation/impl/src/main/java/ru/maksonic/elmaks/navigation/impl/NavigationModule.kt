package ru.maksonic.elmaks.navigation.impl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.elmaks.core.store.KeyStore
import ru.maksonic.elmaks.navigation.api.CityNavigator
import javax.inject.Singleton

/**
 * @author maksonic on 01.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideMainGraphBuilding(keyStore: KeyStore): GraphBuilder = MainGraph(keyStore)

    @Singleton
    @Provides
    fun provideNavService(): CityNavigator = CityNavigator()
}