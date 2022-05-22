package ru.maksonic.elmaks.feature.main.update

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.elmaks.core.elm.ElmRuntime
import ru.maksonic.elmaks.feature.main.model.Cmd
import ru.maksonic.elmaks.feature.main.model.Model
import ru.maksonic.elmaks.feature.main.model.Msg
import ru.maksonic.elmaks.feature.main.program.FetchCitiesProgram
import ru.maksonic.elmaks.feature.main.program.NavigationProgram
import ru.maksonic.elmaks.feature.main.program.SwitchThemeProgram
import ru.maksonic.elmaks.navigation.api.CityNavigator
import javax.inject.Inject

/**
 * @author maksonic on 01.05.2022
 */
typealias Update = Pair<Model, Set<Cmd>>

@HiltViewModel
class MainViewModel @Inject constructor(
    fetchCitiesProgram: FetchCitiesProgram,
    switchThemeProgram: SwitchThemeProgram,
    navigationProgram: NavigationProgram,
    navigator: CityNavigator,
) : ElmRuntime<Model, Msg, Cmd>(
    initialModel = Model(isLoading = true),
    initialCmd = setOf(Cmd.FetchCities),
    subscriptions = listOf(fetchCitiesProgram, switchThemeProgram, navigationProgram),
    navigator = navigator
) {

    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Ui.FetchCityList -> fetchingCityList(model)
            is Msg.Internal.FetchedSuccess -> fetchingSuccess(model, msg)
            is Msg.Ui.OnSwipeRefreshList -> refreshingCityList(model)
            is Msg.Internal.RefreshedSuccess -> refreshedSuccess(model, msg)
            is Msg.Internal.Error -> errorData(model, msg)
            is Msg.Ui.OnCityClicked -> navigateToCity(model, msg)
            is Msg.Ui.ShowSortDialog -> model to emptySet()
            is Msg.Ui.OnSwitchTheme -> switchTheme(model, msg)
            is Msg.Ui.SortFromAtoZ -> sortCitiesFromAtoZ(model)
            is Msg.Ui.SortFromZtoA -> sortCitiesFromZtoA(model)
            is Msg.Ui.SortByPostalCodeAscending -> sortByPostalCodeAscending(model)
            is Msg.Ui.SortByPostalCodeDescending -> sortByPostalCodeDescending(model)
            is Msg.Ui.SearchProcess -> searchingProcess(model, msg)
            is Msg.Ui.ClearSearchingField -> clearSearchingField(model, msg)
        }

    private fun fetchingCityList(model: Model): Update =
        model.copy(
            isLoading = true,
            isSuccessLoading = false,
            isRefreshing = false,
            isErrorLoading = false
        ) to setOf(Cmd.FetchCities)

    private fun fetchingSuccess(model: Model, msg: Msg.Internal.FetchedSuccess): Update =
        model.copy(
            isLoading = false,
            isSuccessLoading = true,
            isRefreshing = false,
            isErrorLoading = false,
            citiesList = msg.fetchedCityList
        ) to emptySet()

    private fun refreshingCityList(model: Model): Update =
        model.copy(isLoading = false, isRefreshing = true, isErrorLoading = false) to setOf(
            Cmd.RefreshCites
        )

    private fun refreshedSuccess(model: Model, msg: Msg.Internal.RefreshedSuccess): Update =
        model.copy(
            isLoading = false,
            isSuccessLoading = true,
            isRefreshing = false,
            isErrorLoading = false,
            citiesList = msg.refreshedCityList
        ) to emptySet()

    private fun errorData(model: Model, msg: Msg.Internal.Error): Update =
        model.copy(
            isLoading = false,
            isSuccessLoading = false,
            isRefreshing = false,
            isErrorLoading = true,
            errorFetchingMsg = msg.errorMsg
        ) to emptySet()

    private fun navigateToCity(model: Model, msg: Msg.Ui.OnCityClicked): Update =
        model to setOf(Cmd.Navigation.ToCity(msg.cityId))

    private fun switchTheme(model: Model, msg: Msg.Ui.OnSwitchTheme): Update =
        model to setOf(Cmd.SwitchTheme(msg.isDarkMode))

    private fun sortCitiesFromAtoZ(model: Model): Update =
        model.copy(citiesList = model.citiesList.sortedBy { it.name }) to emptySet()

    private fun sortCitiesFromZtoA(model: Model): Update =
        model.copy(citiesList = model.citiesList.sortedByDescending { it.name }) to emptySet()

    private fun sortByPostalCodeAscending(model: Model): Update =
        model.copy(citiesList = model.citiesList.sortedBy { it.postalCode }) to emptySet()

    private fun sortByPostalCodeDescending(model: Model): Update =
        model.copy(citiesList = model.citiesList.sortedByDescending { it.postalCode }) to emptySet()

    private fun searchingProcess(model: Model, msg: Msg.Ui.SearchProcess): Update =
        model.copy(
            inputSearchCity = mutableStateOf(model.inputSearchCity.value.copy(msg.query)),
            searchedList = model.citiesList.filter {
                it.name.contains(msg.query, ignoreCase = true)
            }
        ) to emptySet()

    @OptIn(ExperimentalComposeUiApi::class)
    private fun clearSearchingField(model: Model, msg: Msg.Ui.ClearSearchingField): Update =
        model.copy(
            citiesList = model.citiesList,
            searchedList = emptyList(),
            inputSearchCity = mutableStateOf(model.inputSearchCity.value.copy(text = ""))
        ).apply { msg.keyboard?.hide() } to emptySet()
}

