package ru.maksonic.elmaks.feature.main.model

import ru.maksonic.elmaks.core.Command
import ru.maksonic.elmaks.core.FeatureModel
import ru.maksonic.elmaks.core.Message
import ru.maksonic.elmaks.shared.CityUi

/**
 * @author maksonic on 12.05.2022
 */
object MainFeature {
    sealed class Msg : Message {
        object FetchCityList : Msg()
        data class FetchedSuccess(val fetchedCityList: List<CityUi>) : Msg()

        object OnSwipeRefreshList : Msg()
        data class RefreshedSuccess(val refreshedCityList: List<CityUi>) : Msg()

        data class Error(val errorMsg: String) : Msg()

        data class OnSwitchTheme(val isDarkMode: Boolean) : Msg()
        object ShowFilterDialog : Msg()
        data class SearchProcess(val searchable: String) : Msg()
        data class OnCityClicked(val city: (CityUi) -> Unit) : Msg()
    }

    data class Model(
        val isLoading: Boolean = false,
        val isSuccessLoading: Boolean = false,
        val isRefreshing: Boolean = false,
        val isErrorLoading: Boolean = false,
        val cityList: List<CityUi> = emptyList(),
        val filteredList: MutableList<CityUi> = mutableListOf(),
        val errorFetchingMsg: String = ""
    ) : FeatureModel

    sealed class Cmd : Command {
        object LoadCityList : Cmd()
        object RefreshCityList : Cmd()
        data class SwitchTheme(val isDarkMode: Boolean) : Cmd()

        sealed class Navigation : Cmd() {
            data class ToCity(val process: (CityUi) -> Unit) : Navigation()
        }
    }
}