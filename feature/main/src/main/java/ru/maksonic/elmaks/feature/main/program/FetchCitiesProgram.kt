package ru.maksonic.elmaks.feature.main.program

import ru.maksonic.elmaks.core.ElmProgram
import ru.maksonic.elmaks.domain.FetchCitiesUseCase
import ru.maksonic.elmaks.feature.main.model.Cmd
import ru.maksonic.elmaks.feature.main.model.Msg
import ru.maksonic.elmaks.shared.CityDomainToUiMapper
import javax.inject.Inject

/**
 * @author maksonic on 12.05.2022
 */
interface FetchCitiesProgram : ElmProgram<Msg, Cmd> {
    suspend fun fetchCities(consumer: (Msg) -> Unit)
    suspend fun refreshCities(consumer: (Msg) -> Unit)

    class Base @Inject constructor(
        private val fetchCitiesUseCase: FetchCitiesUseCase,
        private val mapper: CityDomainToUiMapper,
    ) : FetchCitiesProgram {
        override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
            when (cmd) {
                is Cmd.LoadCities -> fetchCities(consumer)
                is Cmd.RefreshCites -> refreshCities(consumer)
                else -> {}
            }
        }

        override suspend fun fetchCities(consumer: (Msg) -> Unit) {
            fetchCitiesUseCase().collect { result ->
                result.onSuccess { domainList ->
                    val fetchedUiList = mapper.mapToList(domainList)
                    consumer.invoke(Msg.Internal.FetchedSuccess(fetchedUiList))
                }
                result.onFailure { throwable ->
                    consumer(Msg.Internal.Error(throwable.message.toString()))
                }
            }
        }

        override suspend fun refreshCities(consumer: (Msg) -> Unit) {
            //This query is designed to simulate updated data from the server
            fetchCitiesUseCase().collect { result ->
                result.onSuccess { domainList ->
                    val fetchedUiList = mapper.mapToList(domainList.shuffled())
                    consumer.invoke(Msg.Internal.FetchedSuccess(fetchedUiList))
                }
                result.onFailure { throwable ->
                    consumer(Msg.Internal.Error(throwable.message.toString()))
                }
            }
        }
    }
}