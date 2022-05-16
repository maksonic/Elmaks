package ru.maksonic.elmaks.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author maksonic on 02.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideFilterCitiesUseCase(repository: Repository) : FilterCitiesUseCase =
        FilterCitiesUseCase.Base(repository)
}