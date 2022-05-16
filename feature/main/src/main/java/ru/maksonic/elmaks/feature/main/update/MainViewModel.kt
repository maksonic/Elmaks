package ru.maksonic.elmaks.feature.main.update

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.elmaks.core.ElmRuntime
import ru.maksonic.elmaks.feature.main.model.MainFeature
import ru.maksonic.elmaks.feature.main.program.FetchCitiesProgram
import ru.maksonic.elmaks.feature.main.program.SwitchThemeProgram
import javax.inject.Inject

/**
 * @author maksonic on 01.05.2022
 */
typealias Update = Pair<MainFeature.Model, Set<MainFeature.Cmd>>

@HiltViewModel
class MainViewModel @Inject constructor(
    fetchCitiesProgram: FetchCitiesProgram,
    switchThemeProgram: SwitchThemeProgram,
) : ElmRuntime<MainFeature.Model, MainFeature.Msg, MainFeature.Cmd>(
    initialModel = MainFeature.Model(isLoading = true),
    initialCmd = setOf(MainFeature.Cmd.LoadCityList),
    subscriptions = listOf(fetchCitiesProgram, switchThemeProgram)
) {
    override fun update(msg: MainFeature.Msg, model: MainFeature.Model): Update =
        when (msg) {
            is MainFeature.Msg.FetchCityList -> fetchingCityList(model)
            is MainFeature.Msg.FetchedSuccess -> fetchingSuccess(model, msg)
            is MainFeature.Msg.OnSwipeRefreshList -> refreshingCityList(model)
            is MainFeature.Msg.RefreshedSuccess -> refreshedSuccess(model, msg)
            is MainFeature.Msg.Error -> errorData(model, msg)
            is MainFeature.Msg.OnCityClicked -> navigateToCity(model, msg)
            is MainFeature.Msg.SearchProcess -> searchingProcess(model)
            is MainFeature.Msg.ShowFilterDialog -> model to emptySet()
            is MainFeature.Msg.OnSwitchTheme -> switchTheme(model, msg)
        }

    private fun fetchingCityList(model: MainFeature.Model): Update =
        model.copy(
            isLoading = true,
            isSuccessLoading = false,
            isRefreshing = false,
            isErrorLoading = false
        ) to setOf(MainFeature.Cmd.LoadCityList)

    private fun fetchingSuccess(
        model: MainFeature.Model,
        msg: MainFeature.Msg.FetchedSuccess
    ): Update =
        model.copy(
            isLoading = false,
            isSuccessLoading = true,
            isRefreshing = false,
            isErrorLoading = false,
            cityList = msg.fetchedCityList
        ) to emptySet()

    private fun refreshingCityList(model: MainFeature.Model): Update =
        model.copy(isLoading = false, isRefreshing = true, isErrorLoading = false) to setOf(
            MainFeature.Cmd.RefreshCityList
        )

    private fun refreshedSuccess(
        model: MainFeature.Model,
        msg: MainFeature.Msg.RefreshedSuccess
    ): Update =
        model.copy(
            isLoading = false,
            isSuccessLoading = true,
            isRefreshing = false,
            isErrorLoading = false,
            cityList = msg.refreshedCityList
        ) to emptySet()

    private fun errorData(model: MainFeature.Model, msg: MainFeature.Msg.Error): Update =
        model.copy(
            isLoading = false,
            isSuccessLoading = false,
            isRefreshing = false,
            isErrorLoading = true,
            errorFetchingMsg = msg.errorMsg
        ) to emptySet()

    private fun navigateToCity(
        model: MainFeature.Model,
        msg: MainFeature.Msg.OnCityClicked
    ): Update =
        model to setOf(MainFeature.Cmd.Navigation.ToCity(msg.city))

    private fun searchingProcess(model: MainFeature.Model): Update =
        model to emptySet()

    private fun switchTheme(model: MainFeature.Model, msg: MainFeature.Msg.OnSwitchTheme): Update {
        return model to setOf(MainFeature.Cmd.SwitchTheme(msg.isDarkMode))
    }
}


/*
@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchCitiesUseCase: FetchCitiesUseCase,
    private val filterCitiesUseCase: FilterCitiesUseCase,
    private val themeSetting: AppThemeSetting,
    private val mapper: CityDomainToUiMapper,
) : ViewModel() {

    private var _list = mutableStateOf(emptyList<CityUi>())
    val list: State<List<CityUi>> = _list

    val searchable = mutableStateOf(TextFieldValue(""))

    init {
        viewModelScope.launch {
            val data = mapper.mapToList(fetchCitiesUseCase())
            _list.value = data
        }
    }

    fun setTheme(isDark: Boolean) {
        viewModelScope.launch {
            themeSetting.setTheme(isDark)
        }
    }

    fun searchCity(entered: String) {
        _list.value.filter { it.name == entered }
    }

    fun filterFromAtoZ() = viewModelScope.launch {
        _list.value = mapper.mapToList(filterCitiesUseCase.filterFromAtoZ())
    }

    fun filterFromZtoA() = viewModelScope.launch {
        _list.value = mapper.mapToList(filterCitiesUseCase.filterFromZtoA())
    }

    fun filterPostalCodeAscending() = viewModelScope.launch {
        _list.value = mapper.mapToList(filterCitiesUseCase.filterPostalCodeAscending())
    }

    fun filterPostalCodeDescending() = viewModelScope.launch {
        _list.value = mapper.mapToList(filterCitiesUseCase.filterPostalCodeDescending())
    }
}*/
