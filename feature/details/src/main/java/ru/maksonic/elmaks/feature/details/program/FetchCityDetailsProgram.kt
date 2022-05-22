package ru.maksonic.elmaks.feature.details.program

import androidx.compose.runtime.mutableStateOf
import ru.maksonic.elmaks.core.elm.ElmProgram
import ru.maksonic.elmaks.core.store.ColorGenerator
import ru.maksonic.elmaks.domain.FetchCityDetailsUseCase
import ru.maksonic.elmaks.feature.details.model.Cmd
import ru.maksonic.elmaks.feature.details.model.Msg
import ru.maksonic.elmaks.shared.CityDomainToUiMapper
import javax.inject.Inject

/**
 * @Author maksonic on 21.05.2022
 */
class FetchCityDetailsProgram @Inject constructor(
    private val useCase: FetchCityDetailsUseCase,
    private val mapper: CityDomainToUiMapper,
) : ElmProgram<Msg, Cmd> {

    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchCityInfo -> fetchCityId(consumer, cmd)
            else -> {}
        }
    }

    private suspend fun fetchCityId(consumer: (Msg) -> Unit, cmd: Cmd.FetchCityInfo) {
        useCase(cmd.cityId).collect { tryFindCityById ->
            tryFindCityById.onSuccess { cityDomain ->
                val population = cityDomain.population.toString().plus(" чел.")
                val cityUi = mapper.mapTo(cityDomain)
                consumer(Msg.Internal.CityDetailsSuccess(cityUi.copy(population = population)))
            }
            tryFindCityById.onFailure { throwable ->
                consumer(Msg.Internal.Error(throwable.message.toString()))
            }
        }
    }
}