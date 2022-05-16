package ru.maksonic.elmaks.feature.main.program

import dagger.hilt.android.scopes.ViewModelScoped
import ru.maksonic.elmaks.core.ElmProgram
import ru.maksonic.elmaks.domain.FetchCitiesUseCase
import ru.maksonic.elmaks.feature.main.model.MainFeature
import ru.maksonic.elmaks.shared.CityDomainToUiMapper
import javax.inject.Inject

/**
 * @author maksonic on 12.05.2022
 */
interface FetchCitiesProgram : ElmProgram<MainFeature.Msg, MainFeature.Cmd> {
    suspend fun fetchCityList(consumer: (MainFeature.Msg) -> Unit)
    suspend fun refreshCityList(consumer: (MainFeature.Msg) -> Unit)

    class Base @Inject constructor(
        private val fetchCitiesUseCase: FetchCitiesUseCase,
        private val mapper: CityDomainToUiMapper,
    ) : FetchCitiesProgram {
        override suspend fun execute(cmd: MainFeature.Cmd, consumer: (MainFeature.Msg) -> Unit) {
            when (cmd) {
                is MainFeature.Cmd.LoadCityList -> fetchCityList(consumer)
                is MainFeature.Cmd.RefreshCityList -> refreshCityList(consumer)
                else -> {}
            }
        }

        override suspend fun fetchCityList(consumer: (MainFeature.Msg) -> Unit) {
            fetchCitiesUseCase.invoke().collect { result ->
               result.apply {
                   onSuccess { domainList ->
                       val fetchedUiList = mapper.mapToList(domainList)
                       consumer.invoke(MainFeature.Msg.FetchedSuccess(fetchedUiList))
                   }
                   onFailure { throwable ->
                       consumer(MainFeature.Msg.Error(throwable.message.toString()))
                   }
               }
            }
        }

        override suspend fun refreshCityList(consumer: (MainFeature.Msg) -> Unit) {
            fetchCitiesUseCase.invoke().collect { result ->
               result.apply {
                   onSuccess { domainList ->
                       val fetchedUiList = mapper.mapToList(domainList.shuffled())
                       consumer.invoke(MainFeature.Msg.FetchedSuccess(fetchedUiList))
                   }
                   onFailure { throwable ->
                       consumer(MainFeature.Msg.Error(throwable.message.toString()))
                   }
               }
            }
        }
        /*override suspend fun fetchCityList(consumer: (MainFeature.Msg) -> Unit) {
            fetchCitiesUseCase().apply {
                onSuccess { domainList ->
                    val fetchedUiList = mapper.mapToList(domainList)
                    consumer.invoke(MainFeature.Msg.FetchedSuccess(fetchedUiList))
                }
                onFailure { throwable ->
                    consumer(MainFeature.Msg.Error(throwable.message.toString()))
                }
            }
        }*/

        // TODO: Unlock
       /* override suspend fun refreshCityList(consumer: (MainFeature.Msg) -> Unit) {
            fetchCitiesUseCase().apply {
                onSuccess { domainList ->
                    val refreshedUiList = mapper.mapToList(domainList.shuffled())
                    consumer.invoke(MainFeature.Msg.FetchedSuccess(refreshedUiList))
                }
                onFailure { throwable ->
                    consumer(MainFeature.Msg.Error(throwable.message.toString()))
                }
            }
        }*/
    }
}