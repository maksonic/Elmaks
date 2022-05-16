package ru.maksonic.elmaks.navigation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author maksonic on 01.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideNavigation(): Navigation = Navigation.BaseGraphBuilder()
}