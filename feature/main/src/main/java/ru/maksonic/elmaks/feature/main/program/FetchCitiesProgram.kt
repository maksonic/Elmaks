package ru.maksonic.elmaks.feature.main.program

import kotlinx.coroutines.flow.Flow
import ru.maksonic.elmaks.core.elm.ElmProgram
import ru.maksonic.elmaks.domain.CityDomain
import ru.maksonic.elmaks.domain.FetchCitiesUseCase
import ru.maksonic.elmaks.domain.FetchCloudCitiesUseCase
import ru.maksonic.elmaks.feature.main.model.Cmd
import ru.maksonic.elmaks.feature.main.model.Msg
import ru.maksonic.elmaks.shared.CityDomainToUiMapper
import javax.inject.Inject

/**
 * @author maksonic on 12.05.2022
 */
interface FetchCitiesProgram : ElmProgram<Msg, Cmd> {
    suspend fun fetchCities(consumer: (Msg) -> Unit, useCase: Flow<Result<List<CityDomain>>>)

    class Base @Inject constructor(
        private val fetchCloudCitiesUseCase: FetchCloudCitiesUseCase,
        private val fetchCitiesUseCase: FetchCitiesUseCase,
        private val mapper: CityDomainToUiMapper,
    ) : FetchCitiesProgram {
        override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
            when (cmd) {
                is Cmd.FetchCities -> fetchCities(consumer, fetchCitiesUseCase())
                is Cmd.RefreshCites -> fetchCities(consumer, fetchCloudCitiesUseCase())
                else -> {}
            }
        }

        override suspend fun fetchCities(
            consumer: (Msg) -> Unit,
            useCase: Flow<Result<List<CityDomain>>>
        ) {
            useCase.collect { result ->
                result.onSuccess { domainList ->
                    val fetchedUiList = mapper.mapToList(domainList)
                    consumer.invoke(Msg.Internal.FetchedSuccess(fetchedUiList))
                }
                result.onFailure { throwable ->
                    consumer(Msg.Internal.Error(throwable.message.toString()))
                }
            }
        }
    }
}