package ru.maksonic.elmaks.feature.main.model

import ru.maksonic.elmaks.core.Command
import ru.maksonic.elmaks.core.StateModel
import ru.maksonic.elmaks.core.Message
import ru.maksonic.elmaks.shared.CityUi

/**
 * @author maksonic on 12.05.2022
 */
sealed class Msg : Message {

    sealed class Ui : Msg() {
        object FetchCityList : Ui()
        object OnSwipeRefreshList : Ui()
        data class OnSwitchTheme(val isDarkMode: Boolean) : Ui()
        object ShowSortDialog : Ui()
        object SortFromAtoZ : Ui()
        object SortFromZtoA : Ui()
        object SortByPostalCodeAscending : Ui()
        object SortByPostalCodeDescending : Ui()
        data class OnCityClicked(val city: (CityUi) -> Unit) : Ui()
        data class SearchProcess(val searchable: String) : Msg()

    }

    sealed class Internal : Msg() {
        data class FetchedSuccess(val fetchedCityList: List<CityUi>) : Msg()
        data class RefreshedSuccess(val refreshedCityList: List<CityUi>) : Msg()
        data class Error(val errorMsg: String) : Msg()
    }
}

