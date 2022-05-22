package ru.maksonic.elmaks.feature.details.update

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.elmaks.core.elm.ElmRuntime
import ru.maksonic.elmaks.core.store.AppThemeSetting
import ru.maksonic.elmaks.core.store.KeyStore
import ru.maksonic.elmaks.feature.details.model.Cmd
import ru.maksonic.elmaks.feature.details.model.Model
import ru.maksonic.elmaks.feature.details.model.Msg
import ru.maksonic.elmaks.feature.details.program.FetchCityDetailsProgram
import ru.maksonic.elmaks.feature.details.program.GenerateCityCardProgram
import ru.maksonic.elmaks.navigation.api.CityNavigator
import javax.inject.Inject

/**
 * @Author maksonic on 18.05.2022
 */
typealias Update = Pair<Model, Set<Cmd>>

@HiltViewModel
class DetailsViewModel @Inject constructor(
    fetchCityDetailsProgram: FetchCityDetailsProgram,
    generateCityCardProgram: GenerateCityCardProgram,
    appThemeSetting: AppThemeSetting,
    keyStore: KeyStore,
    navigator: CityNavigator
) : ElmRuntime<Model, Msg, Cmd>(
    initialModel = Model(),
    initialCmd = setOf(
        Cmd.FetchCityInfo(navigator.getArgument(keyStore.passedCityIdKey).toLong()),
        Cmd.CardColorBackgroundGeneration(mutableStateOf(appThemeSetting.currentTheme.value))
    ),
    subscriptions = listOf(fetchCityDetailsProgram, generateCityCardProgram),
    navigator = navigator
) {

    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Internal.FetchCityDetails -> model to setOf(Cmd.FetchCityInfo(msg.cityId))
            is Msg.Internal.CityDetailsSuccess -> successFetching(model, msg)
            is Msg.Internal.Error -> errorFetching(model, msg)
            is Msg.Ui.RetryFetchCityDetails -> retryFetchDetails(model, msg)
            is Msg.Internal.GenerateCardColor -> {
                model to setOf(Cmd.CardColorBackgroundGeneration(msg.isDarkMode))
            }
            is Msg.Internal.ApplyCityCardColor -> model.copy(cardBgColor = msg.color) to emptySet()
            is Msg.Ui.ShowKladrInfoDialog -> {
                model.copy(isShowKladrDialog = mutableStateOf(true)) to emptySet()
            }
        }

    private fun retryFetchDetails(model: Model, msg: Msg.Ui.RetryFetchCityDetails): Update =
        model.copy(
            isLoading = true,
            isSuccessCityDetails = false,
            isError = false
        ) to setOf(Cmd.FetchCityInfo(msg.cityId))

    private fun successFetching(model: Model, msg: Msg.Internal.CityDetailsSuccess): Update =
        model.copy(
            isLoading = false,
            city = msg.city,
            isSuccessCityDetails = true,
            isError = false
        ) to emptySet()

    private fun errorFetching(model: Model, msg: Msg.Internal.Error): Update =
        model.copy(
            isLoading = false,
            isSuccessCityDetails = false,
            isError = true,
            errorMessage = msg.message
        ) to emptySet()
}
