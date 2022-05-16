package ru.maksonic.elmaks.feature.main.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.elmaks.core.store.AppThemeSetting
import ru.maksonic.elmaks.domain.FetchCitiesUseCase
import ru.maksonic.elmaks.feature.main.program.FetchCitiesProgram
import ru.maksonic.elmaks.feature.main.program.SwitchThemeProgram
import ru.maksonic.elmaks.shared.CityDomainToUiMapper
import javax.inject.Singleton

/**
 * @author maksonic on 12.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object MainFeatureModule {

    @Singleton
    @Provides
    fun provideFetchCitiesProgram(
        useCase: FetchCitiesUseCase,
        mapper: CityDomainToUiMapper,
    ): FetchCitiesProgram = FetchCitiesProgram.Base(useCase, mapper)

    @Singleton
    @Provides
    fun provideSwitchThemeProgram(themeSetting: AppThemeSetting): SwitchThemeProgram =
        SwitchThemeProgram.Base(themeSetting)
}