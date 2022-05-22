package ru.maksonic.elmaks.feature.main.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import ru.maksonic.elmaks.core.elm.Message
import ru.maksonic.elmaks.shared.CityUi

/**
 * @author maksonic on 12.05.2022
 */
sealed class Msg : Message {

    @Immutable
    sealed class Ui : Msg() {
        object FetchCityList : Ui()
        object OnSwipeRefreshList : Ui()
        data class OnSwitchTheme(val isDarkMode: Boolean) : Ui()
        object ShowSortDialog : Ui()
        object SortFromAtoZ : Ui()
        object SortFromZtoA : Ui()
        object SortByPostalCodeAscending : Ui()
        object SortByPostalCodeDescending : Ui()
        data class OnCityClicked(val cityId: Long) : Ui()
        data class SearchProcess(val query: String) : Ui()
        @OptIn(ExperimentalComposeUiApi::class)
        data class ClearSearchingField(val keyboard: SoftwareKeyboardController?) : Ui()
    }

    @Immutable
    sealed class Internal : Msg() {
        data class FetchedSuccess(val fetchedCityList: List<CityUi>) : Internal()
        data class RefreshedSuccess(val refreshedCityList: List<CityUi>) : Internal()
        data class Error(val errorMsg: String) : Internal()
    }
}

