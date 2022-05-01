package ru.maksonic.elmaks.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.elmaks.core.di.IoDispatcher
import ru.maksonic.elmaks.domain.CitiesRepository
import javax.inject.Singleton

/**
 * @author makosnic on 01.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideCitiesRepository(@IoDispatcher dp: CoroutineDispatcher): CitiesRepository =
        BaseCitiesRepository(dp)
}