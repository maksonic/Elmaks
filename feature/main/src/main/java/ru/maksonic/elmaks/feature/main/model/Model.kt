package ru.maksonic.elmaks.feature.main.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import ru.maksonic.elmaks.core.elm.StateModel
import ru.maksonic.elmaks.shared.CityUi

/**
 * @Author maksonic on 16.05.2022
 */
@Immutable
data class Model(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isRefreshing: Boolean = false,
    val isError: Boolean = false,
    val errorFetchingMsg: String = "",
    val inputSearchCity: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue("")),
    val citiesList: List<CityUi> = emptyList(),
    val searchedList: List<CityUi> = emptyList(),
    val isShowSortDialog: MutableState<Boolean> = mutableStateOf(false)
) : StateModel